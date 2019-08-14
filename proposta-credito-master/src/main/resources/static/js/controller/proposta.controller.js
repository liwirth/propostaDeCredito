app.controller("propostaController", function($scope, PropostaService){
	$scope.propostas = [];
    $scope.proposta = {};
    $scope.filter = "";
    $scope.saved = false;
    $scope.error = false;
    
    $scope.findAllPropostas = function(){
        console.log("teste list");
		PropostaService.findAllPropostas().then(function(data){            
            $scope.propostas = data;
            console.log($scope.propostas);
		});
    };
    
    $scope.findByCpf = function(){
        console.log("buscou"+ $scope.filter);
        if($scope.filter){
            PropostaService.findByCpf($scope.filter).then(function(data){
			    $scope.propostas = data;
		    });
        }else{
            PropostaService.findAllPropostas().then(function(data){            
                $scope.propostas = data;
                console.log($scope.propostas);
		    });
        }		
	};

    $scope.addProposta = function(){
        console.log("teste add");
		PropostaService.addProposta($scope.proposta).then(function(data){
			$scope.saved = true;
		});		
		$scope.proposta = {};
    };

    $scope.analisarProposta = function(proposta){
        console.log("teste add");
		PropostaService.analisarProposta(proposta).then(function(){
			PropostaService.findByCpf(proposta.cpf).then(function(data){
                $scope.propostas = data;
                $scope.filter = proposta.cpf;
		    });
		});		
		$scope.proposta = {};
    };
    
    $scope.findAllPropostas();

});