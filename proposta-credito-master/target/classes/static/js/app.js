var app = angular.module("propostaApp", ["ngRoute"]);

app.config(['$routeProvider', function($routeProvider) {

	$routeProvider
		.when('/', {
			templateUrl : 'html/home.html',
			controller: 'homeController'
		})		
        .when('/cadastrarProposta', {
			templateUrl : 'html/proposta.html',
			controller: 'propostaController'
        })	
        .when('/propostasAnalise', {
			templateUrl : 'html/proposta-list.html',
			controller: 'propostaController'
        })        
		.otherwise({
			redirectTo: '/'
		});
}]);