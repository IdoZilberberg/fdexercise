'use strict';

/**
 * FarmController
 * @constructor
 */
var FarmController = function ($scope, $http) {
  $scope.farm = {};

  $scope.submitForm = function (farm) {
    $scope.resetError();

    $http.get('/stats', {params: {zipcode:$scope.farm.zipcode, seedingDate:$scope.farm.seedingDate}})
      .success(function(response) {
        $scope.temp_min = response.temp_min
        $scope.temp_max = response.temp_max
        $scope.precip_in = response.precip_in
      }).error(function() {
      $scope.setError('Could not get farm stats');
    })
  };

  $scope.resetFarmForm = function () {
    $scope.resetError();
    $scope.farm = {};
  };

  $scope.resetError = function () {
    $scope.error = false;
    $scope.errorMessage = '';
  };

  $scope.setError = function (message) {
    $scope.error = true;
    $scope.errorMessage = message;
  };

  $scope.predicate = 'id';
};