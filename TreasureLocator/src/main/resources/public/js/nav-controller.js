//Created by Malgorzata Sliwa 2018

angular.module('navController', [])
	.controller('nav', function($scope, $state) {
		$scope.title = 'Treasures';

		// if the current router url matches the passed in url, true is returned
		$scope.isUrl = function(url) {
			if (url === '#') return false;
			return ('#' + $state.$current.url.source + '/').indexOf(url + '/') === 0;
		};

		$scope.pages = [
			{
				name: 'Home',
				url: '#/'
			},
			{
				name: 'Treasures',
				url: '#/treasures'
			}
		]
	});
