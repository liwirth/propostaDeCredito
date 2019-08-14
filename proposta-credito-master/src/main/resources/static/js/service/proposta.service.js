app.service("PropostaService",function($http, API_ENDPOINT){

	this.findAllPropostas = function(){
		return $http.get(API_ENDPOINT.PROPOSTAS).then(function(response) {
			return response.data;
		}, function(errResponse) {
			console.log("An exception occured", errResponse);
			return errResponse;
		});
	}
	
	this.addProposta = function(proposta){
		return $http.post(API_ENDPOINT.PROPOSTAS, proposta).then(function(response) {
			return response.data;
		}, function(errResponse) {
			console.log("An exception occured", errResponse);
			return errResponse;
		});
	}
	
	this.findByCpf = function(cpf){
		return $http.get(API_ENDPOINT.PROPOSTAS + "/filter/" + cpf).then(function(response) {
			return response.data;
		}, function(errResponse) {
			console.log("An exception occured", errResponse);
			return errResponse;
		});
	}
	
	this.analisarProposta = function(proposta){
		return $http.put(API_ENDPOINT.PROPOSTAS + "/" + proposta.id, proposta).then(function(response) {
			return response.data;
		}, function(errResponse) {
			console.log("An exception occured", errResponse);
			return errResponse;
		});
	}
});