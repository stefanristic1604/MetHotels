$(document).ready(function () {
    var hanlder = function () {
        $.ajax({
            url: 'services/PaginationGridRezervacija?page=' + $(this).text(),
            success: function (data) {
                $('#table').empty();
                $('#table').append(data);
                $(".callservice").bind("click", hanlder);
            }
        });
    };
    $.ajax({
        url: 'services/PaginationGridRezervacija?page=1',
        success: function (data) {
            $('#table').empty();
            $('#table').append(data);
            $(".callservice").bind("click", hanlder);
        }
    });
});

