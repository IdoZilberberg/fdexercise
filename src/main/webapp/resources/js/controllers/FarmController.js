'use strict';

/**
 * FarmController
 * @constructor
 */

var map;

var FarmController = function ($scope, $http) {
  $scope.farm = {};
  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

  $scope.submit = function (farm) {
    $scope.resetError();

    $http.get('farms/stats', {params: {zipcode:$scope.farm.zipcode, seedingDate:$scope.farm.seedingDate}})
      .success(function(response) {
        $scope.farm.temp_min = response.tempMin;
        $scope.farm.temp_max = response.tempMax;
        $scope.farm.precip_in = response.precip;
        $scope.farm.lat = response.lat;
        $scope.farm.lng = response.lng;
        $scope.resetMap(farm.lat, farm.lng);
      }).error(function() {
      $scope.setError('Could not get farm stats');
    })
  };


  $scope.resetForm = function () {
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

  $scope.resetMap = function(lat, lng)  {

    debugger;
    var center = new google.maps.LatLng(lat, lng);
    map.panTo(center);
  }

  $scope.predicate = 'id';
};