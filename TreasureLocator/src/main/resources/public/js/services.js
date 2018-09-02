//Created by Malgorzata Sliwa 2018

angular.module('app.services', []).factory('Treasure', function($resource) {
  return $resource('/api/v1/treasures/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    }
  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});
