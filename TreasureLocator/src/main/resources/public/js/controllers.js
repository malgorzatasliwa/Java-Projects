//Created by Malgorzata Sliwa 2018

angular.module('app.controllers', []).controller('TreasureListController', function($scope, $state, popupService, $window, Treasure) {
  $scope.treasures = Treasure.query(); // Sends a GET to /api/vi/treasures and fetch all treasures

  $scope.deleteTreasure = function(treasure) { // Sends a DELETE to /api/v1/treasures/:id and delete a treasure.
    if (popupService.showPopup('Really delete this?')) {
      treasure.$delete(function() {
        $scope.treasures = Treasure.query();
        $state.go('treasures');
      });
    }
  };
}).controller('TreasureViewController', function($scope, $stateParams, Treasure) {
  $scope.treasure = Treasure.get({ id: $stateParams.id }); //Sends a GET to /api/v1/treasures/:id and get a single treasure.
}).controller('TreasureCreateController', function($scope, $state, $stateParams, Treasure) {
  $scope.treasure = new Treasure();  // Create new treasure instance. Properties will be set via ng-model on UI

  $scope.addTreasure = function() { //Sends a POST to /api/v1/treasure and screate a new treasure.
    $scope.treasure.$save(function() {
      $state.go('treasures'); // on success go back to the list i.e. treasures state.
    });
  };
}).controller('TreasureEditController', function($scope, $state, $stateParams, Treasure) {
  $scope.updateTreasure = function() { // Sends a PUT to /api/v1/treasures/:id and update the edited treasure.
    $scope.treasure.$update(function() {
      $state.go('treasures'); // on success go back to the list i.e. treasures state.
    });
  };

  $scope.loadTreasure = function() { // Sends a GET request to /api/v1/treasures/:id to get a treasures to update
    $scope.treasure = Treasure.get({ id: $stateParams.id });
  };

  $scope.loadTreasure(); // Load a treasure which can be edited on UI
});
