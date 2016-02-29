'use strict';

angular.module('diggerUiApp', [
  'ngRoute',
  'ngCookies',
  'formsAngular',
  'ngResource',
  'ngSanitize',
  'ui.bootstrap',
  'ui.date',
  'ngGrid',
  'ngCkeditor',
  'ui.select2',
  'uploadModule'
])
  .config(function ($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/'});

  
  });

  formsAngular.config(['cssFrameworkServiceProvider', 'routingServiceProvider', function (cssFrameworkService, routingService) {
      routingService.start(
        {
          html5Mode: true,

          prefix:'/data',
          routing: 'ngroute'
        }
      );
      cssFrameworkService.setOptions({framework: 'bs3'});
      }]);
