

describe("API_ENDPOINT", function(){
	var apiEndPoint;
	beforeEach(module('propostaApp'));
	
	beforeEach(inject(function(API_ENDPOINT){
		apiEndPoint = API_ENDPOINT;
	}));
	
	it("Deve estar válido", function(){
		expect(apiEndPoint).toBeDefined();
	});
	
	it("API_ENDPOINT.ROOT should be /", function(){
		expect(apiEndPoint.ROOT).toBe("/");
	});
	
	it("API_ENDPOINT.TODOS should be /", function(){
		expect(apiEndPoint.PROPOSTAS).toBe("/propostas");
	});
});