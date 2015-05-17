(function ($) {
    T5.extendInitializers(function () {
        function confirmation(spec) {
            $("#" + spec.id).bind("click", function (e) {
                if (!confirm(spec.message))
                    e.preventDefault();
            });
        }
        return {confirmation: confirmation}
    });
})(jQuery);

