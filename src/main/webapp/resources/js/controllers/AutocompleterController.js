'use strict';

angular.module('AutocompleterApp', []).controller('AutocompleterController', function ($scope, $http) {
  $scope.findAutocomleteWords = function(word) {
    $http.get('/autocompleter/complete/',{params: {word: word}}).success(function(wordList) {
      $scope.words = wordList;
    });
  }
})