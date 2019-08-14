
describe("PropostaController Tests", function(){
	var scope;
	var propostaCtrl;

	beforeEach(module('propostaApp'));

	beforeEach(inject(function($controller, $rootScope){
		scope = $rootScope.$new();
		propostaCtrl = $controller('PropostaController', {$scope: scope});
	}));
	 
	 it("Deve estar válido", function(){
		expect(propostaCtrl).toBeDefined(); 
	 });
	 
	 it("deve possui método de listagem", function(){
		expect(propostaCtrl.findAllPropostas).toBeDefined();
	 });
	 
	 
});