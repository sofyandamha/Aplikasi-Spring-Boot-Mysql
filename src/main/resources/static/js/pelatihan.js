var AplikasiTutorialSpring = angular.module('AplikasiTutorialSpring',[]);

AplikasiTutorialSpring.controller('HaloController', function($scope){

    $scope.daftarEmail = [
        'sofyandamha@gmail.com'
        
    ];
    $scope.tambahEmail = function(){
        $scope.daftarEmail.push($scope.email);
    };
    $scope.hapusEmail = function (x){
        var index = $scope.daftarEmail.indexOf(x);
        if (index > -1){
            $scope.daftarEmail.splice(index, 1);
        }
    };
});

AplikasiTutorialSpring.controller('MateriController', function($http,$scope){
    $scope.dataMateri ={};
    
    
    $scope.simpanMateri = function (){
      $http.post('/api/materi', $scope.materi).then (sukses, gagal);
      function sukses(response){
            $scope.updateDataMateri();
        }
        function gagal(response){
            console.log($scope.dataMateri);
            alert('Error : '+ response);
        };
    };
    
    $scope.hapusMateri = function (x){
      $http.delete('/api/materi/'+x.id).then (sukses, gagal);
        function sukses(response){
            $scope.updateDataMateri();
        }
        function gagal(response){
            console.log($scope.dataMateri);
            alert('Error : '+ response);
        };
        
    };
    
    $scope.updateDataMateri = function(){
      $http.get('/api/materi').then (sukses, gagal);
        function sukses(response){
            $scope.dataMateri = response.data;
            console.log($scope.dataMateri);
        };
        
        function gagal(response){
            console.log($scope.dataMateri);
            alert('Error : '+ response);
        };
    };
    $scope.updateDataMateri();
});