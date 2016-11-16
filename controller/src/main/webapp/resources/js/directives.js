(function() {
  angular
  .module('app')
  .directive('userContent', function () {
    return {
      restrict: 'E',
      templateUrl: 'user-content.html'
    };
  });
})();