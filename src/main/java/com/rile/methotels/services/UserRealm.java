package com.rile.methotels.services;

import com.rile.methotels.entities.Korisnik;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
/**
 *
 * @author Stefan
 */
public class UserRealm extends AuthorizingRealm {

    Session session;

    public UserRealm(Session session) {
        super(new MemoryConstrainedCacheManager());
        setName("localaccounts");
        this.session = session;
        setAuthenticationTokenClass(UsernamePasswordToken.class);
        setCredentialsMatcher(new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME));
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException(
                    "PrincipalCollection was null, which should not happen");
        }
        if (principals.isEmpty()) {
            return null;
        }
        if (principals.fromRealm(getName()).size() <= 0) {
            return null;
        }
        String username = (String) principals.fromRealm(getName()).iterator().next();
        System.out.println("Username is" + username);
        if (username == null) {
            return null;
        }
        Korisnik user = findByUsername(username);
        if (user == null) {
            return null;
        }
        Set<String> roles = new HashSet<String>(1);
        roles.add(user.getRola().name());
        return new SimpleAuthorizationInfo(roles);
    }

    public String getMD5Hash(String yourString) {
        try {
            java.security.MessageDigest md
                    = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(yourString.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,
                        3));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        System.out.println(getMD5Hash(new String(upToken.getPassword())));
        // Null username is invalid
        Korisnik user = checkUser(username, getMD5Hash(new String(upToken.getPassword())));
        if (user == null) {
            System.out.println("user is nul");
            throw new IncorrectCredentialsException();
        }
        Set<String> roles = new HashSet<String>(1);
        roles.add(user.getRola().name());
        return new SimpleAuthenticationInfo(user.getEmail(), new String(user.getLozinka()), getName());
    }

    private Korisnik checkUser(String username, String email) {
        try {
            Korisnik u = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("korisnickoIme",
                    username)).add(Restrictions.eq("email", email)).uniqueResult();
            if (u != null) {
                return u;
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Korisnik findByUsername(String username) {
        List<Korisnik> users
                = session.createCriteria(Korisnik.class).add(Restrictions.eq("korisnickoIme", username)).list();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

}
