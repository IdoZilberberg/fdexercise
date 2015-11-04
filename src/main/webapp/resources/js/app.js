'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
  $routeProvider.when('/farms', {
    templateUrl: 'farms/layout',
    controller: FarmController
  });
  $routeProvider.otherwise({redirectTo: '/farms'});
}]);
