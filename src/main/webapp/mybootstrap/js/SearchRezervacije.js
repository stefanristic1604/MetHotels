$(document).ready(function () {
    $("#pretraga").keyup(function () {
        var value = $(this).val();
        if (value != "")
            $.ajax({
                url: 'services/FindRezervacija?ime=' + value,
                data: {
                    ime: value
                },
                success: function (data) {
                    $('#info').empty();
                    $('#info').append(data);
                }
            });
    });
});