//Created by Malgorzata Sliwa 2018

(function() {
	var app = angular.module('app', ['ui.router', 'navController', 'ngAnimate', 'ui.bootstrap', 'ngResource', 'app.controllers', 'app.services'])

	// requirejs is a JavaScript library that helps in lazily loading JavaScript dependencies
	// define for requirejs loaded modules
	define('app', [], function() { return app; });

	// function for dynamic load with requirejs of a javascript module for use with a view
	function req(deps) {
		if (typeof deps === 'string') deps = [deps];
		return {
			deps: function ($q, $rootScope) {
				var deferred = $q.defer();
				require(deps, function() {
					$rootScope.$apply(function () {
						deferred.resolve();
					});
					deferred.resolve();
				});
				return deferred.promise;
			}
		}
	}

	app.config(function($stateProvider, $urlRouterProvider, $controllerProvider){
		var origController = app.controller
		app.controller = function (name, constructor){
			$controllerProvider.register(name, constructor);
			return origController.apply(this, arguments);
		}

		var viewsPrefix = 'views/';

		// user will be send to / for any unmatched url
		$urlRouterProvider.otherwise("/")

		$stateProvider
			.state('home', {
				url: "/",
				templateUrl: viewsPrefix + "home.html",
				data: {
					pageTitle: 'Home'
				}
			})
			.state('treasures',{
	        url:'/treasures',
	        templateUrl: viewsPrefix + 'treasures.html',
	        controller:'TreasureListController'
	    }).state('viewTreasure',{
	       url:'/treasures/:id/view',
	       templateUrl: viewsPrefix + 'treasure-view.html',
	       controller:'TreasureViewController'
	    }).state('newTreasure',{
	        url:'/treasures/new',
	        templateUrl: viewsPrefix + 'treasure-add.html',
	        controller:'TreasureCreateController'
	    }).state('editTreasure',{
	        url:'/treasures/:id/edit',
	        templateUrl: viewsPrefix + 'treasure-edit.html',
	        controller:'TreasureEditController'
	    })
	})
	.directive('updateTitle', ['$rootScope', '$timeout',
		function($rootScope, $timeout) {
			return {
				link: function(scope, element) {
					var listener = function(event, toState) {
						var title = 'Project Name';
						if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle + ' - ' + title;
						$timeout(function() {
							element.text(title);
						}, 0, false);
					};

					$rootScope.$on('$stateChangeSuccess', listener);
				}
			};
		}
	]);
}());
