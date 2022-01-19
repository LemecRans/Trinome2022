//var atoo="bobo";
function roughScale(x, base){
    const parsed = Number.parseInt(x,base);
    if(Number.isNaN(parsed)){
        return 0;
    }
    const valeur =  (parsed + 1);
    return valeur;
}
function signalement($scope,$http){
    var atoo="bobo aussi";
    $http.get('http://localhost:8080/listeSignalement')
    .success(function(data){
        atoo="bobo aussi";
        $scope.listeSignalement=data;
    })
    $http.get('http://localhost:8080/listeRegion')
        .success(function(data){
            $scope.listeRegion=data;
    })
    $http.get('http://localhost:8080/listeStatut')
        .success(function(data){
            $scope.listeStatut=data;
    })
   $scope.deleteSignalement=function($chiffre){
//        console.log("Ato ambony : "+$chiffre);
        $http.get('http://localhost:8080/deleteSignalement/'+$chiffre)
        .success(function(data){
            $scope.deleteSignaux=data;
            alert('Vofafa lelike😂😂 ');
//            console.log("Ato ambany : "+$chiffre);
        })
    }
    $scope.updateSignalement=function($chiffre){
        var etat = document.getElementById('Statut').options[document.getElementById('Statut').selectedIndex].value
        localStorage.setItem("statut",Statut);
        const tenaEtat = roughScale(etat, 10);
        console.log("new etat : "+tenaEtat);
        console.log("Ato ambony : "+$chiffre);
        $http.get('http://localhost:8080/updateSignalement/'+$chiffre+'/'+tenaEtat)
        .success(function(data){
            $scope.updateSignaux=data;
            alert('Vita le update 😊😊 ');
        })
        
//      console.log("Chiffre : "+$chiffre);
//        console.log("Ato ambany : "+tenaEtat);
    }
}
function region($scope,$http){
    $http.get('http://localhost:8080/listeRegion')
        .success(function(data){
            $scope.listeRegion=data;
    })
   $scope.deleteRegion=function($chiffre){
        $http.get('http://localhost:8080/deleteRegion/'+$chiffre)
        .success(function(data){
            $scope.deleteSignaux=data;
            alert('Vohafafa 😂😂 ');
//            console.log("Ato ambany : "+$chiffre);
        })
    }
    $scope.ajouterRegion=function(){
        var designationRegion = document.getElementById('designationRegion').value;
        localStorage.setItem("designationRegion",designationRegion);
        var coordonneeX = document.getElementById('coordonneX').value;
        localStorage.setItem("coordonneX",coordonneX);
        var coordonneeY = document.getElementById('coordonneY').value;
        localStorage.setItem("coordonneY",coordonneY);
        var coordonneeX1 = document.getElementById('coordonneX1').value;
        localStorage.setItem("coordonneX1",coordonneX1);
        var coordonneeY1 = document.getElementById('coordonneY1').value;
        localStorage.setItem("coordonneY1",coordonneY1);
        $http.get('http://localhost:8080/insertRegion/'+designationRegion+'/'+coordonneeX+'/'+coordonneeY+'/'+coordonneeX1+'/'+coordonneeY1)
        .success(function(data){
            $scope.liste=data;
            alert('Donnee inserer 😂😂 ');
        })
    }
}   

function controlRegion($scope,$http){
    $scope.affichageRegion=function(){
        $http.get('http://localhost:8080/listeRegion')
        .success(function(data){
            $scope.listeRegion=data;
        })
    }
}

function controlStat($scope,$http){
    var region;
    var labe=[];
    var val=[];
    function lab(){
        for (var i = 0; i < region.length; i++) {
            labe[i]= region[i].designationProbleme;
            val[i]= region[i].pourcentage;
        }
    }
    $scope.affichageStatistique=function(){
        $http.get('http://localhost:8080/listeStat')
        .success(function(data){
            $scope.listeStatistique=data;
            region=$scope.listeStatistique;
            lab();
            console.log(labe);
        })
    }
    $scope.affichageStatByRegion=function($id){
        $http.get('http://localhost:8080/listeliste/'+$id)
        .success(function(data){
            $scope.listeStat=data;
        })
    }

    $http.get('http://localhost:8080/listeRegion')
        .success(function(data){
            $scope.listeRegion=data;
        })

    $scope.affichageStatByRegion=function($id){
        $http.get('http://localhost:8080/listeliste/'+$id)
        .success(function(data){
            $scope.listeStat=data;
        })
    }

    $http.get('http://localhost:8080/listeRegion')
        .success(function(data){
            $scope.listeRegion=data;
        })
    $scope.chart=function(){
        $http.get('http://localhost:8080/listeStat')
        .success(function(data){
            $scope.listeStatistique=data;
            region=$scope.listeStatistique;
            lab();
            console.log(labe);
        })
        var options = {
            chart: {
                height: 320,
                type: 'donut',
            },
            labels: labe,
            series: val,
            colors: ["#4680ff", "#0e9e4a", "#00acc1", "#ffba57", "#ff5252"],
            legend: {
                show: true,
                position: 'bottom',
            },
            plotOptions: {
                pie: {
                    donut: {
                        labels: {
                            show: true,
                            name: {
                                show: true
                            },
                            value: {
                                show: true
                            }
                        }
                    }
                }
            },
            dataLabels: {
                enabled: true,
                dropShadow: {
                    enabled: false,
                }
            },
            responsive: [{
                breakpoint: 480,
                options: {          
                    legend: {
                        position: 'bottom'
                    }
                }
            }]
        }
        var chart = new ApexCharts(
            document.querySelector("#pie-chart-2"),
            options
        );
        chart.render();
    };
    $scope.chart1=function(){
        $http.get('http://localhost:8080/listeStat')
        .success(function(data){
            $scope.listeStatistique=data;
            region=$scope.listeStatistique;
            lab();
            console.log(labe);
        })
        var options = {
            chart: {
                height: 350,
                type: 'bar',
            },
            plotOptions: {
                bar: {
                    horizontal: false,
                    columnWidth: '55%',
                    endingShape: 'rounded'
                },
            },
            dataLabels: {
                enabled: true
            },
            colors: ["#ffba57"],
            stroke: {
                show: false,
                width: 2,
                colors: ['transparent']
            },
            series: [ {
                name: 'Problème signalé',
                data: val,
            }, ],
            xaxis: {
                categories: labe,
            },
            yaxis: {
                title: {
                    text: 'Statistique globale'
                }
            },
            fill: {
                opacity: 1

            },
            tooltip: {
                y: {
                    formatter: function(val) {
                        return  + val + "%"
                    }
                }
            }
        }
        var chart = new ApexCharts(
            document.querySelector("#bar-chart-1"),
            options
        );
        chart.render();
    };
}

function controlRecherche($scope,$http){
    $http.get('http://localhost:8080/listeRegion')
        .success(function(data){
            $scope.listeRegion=data;
    })
    $http.get('http://localhost:8080/listeStatut')
        .success(function(data){
            $scope.listeStatut=data;
    })
    $http.get('http://localhost:8080/lista')
        .success(function(data){
            $scope.listeProb=data;
    })
    $scope.listeProbleme=null;
    $scope.pasResu=false;
    $scope.resu=false;
    $scope.erreur=false;
    $scope.listeRechercheRegion=function(){
        $http.get('http://localhost:8080/listeRechercheRegion/'+$scope.recherche)
        .success(function(data){
            $scope.erreur=false;
            $scope.recherche="";
            if (data!='err'){
                if (data!=''){
                    $scope.listeRegion=data;
                    $scope.resu=true;
                    $scope.pasResu=false;
                }
                else{
                    $scope.resu=false;
                    $scope.pasResu=true;
                }
            }
            else{
                $scope.resu=false;
                $scope.pasResu=false;
                $scope.erreur=true;
            }
        })  
    }

    $scope.listeRechercheProbleme=function(){
        console.log('http://localhost:8080/listeRechercheProbleme/'+$scope.recherche);
        $http.get('http://localhost:8080/listeRechercheProbleme/'+$scope.recherche)
        .success(function(data){
            $scope.erreur=false;
            $scope.recherche="";
            if (data!='err'){
                if (data!=''){
                    $scope.listeProbleme=data;
                    $scope.resu=true;
                    $scope.pasResu=false;
                }
                else{
                    $scope.resu=false;
                    $scope.pasResu=true;
                }
            }
            else{
                $scope.resu=false;
                $scope.pasResu=false;
                $scope.erreur=true;
            }
        })  
    }

    $scope.listeRechercheProblemeParRegion=function(){
        $http.get('http://localhost:8080/listeRechercheProblemeParRegion/'+$scope.recherche)
        .success(function(data){
            $scope.erreur=false;
            $scope.recherche="";
            if (data!='err'){
                if (data!=''){
                    $scope.listeProbleme=data;
                    $scope.resu=true;
                    $scope.pasResu=false;
                }
                else{
                    $scope.resu=false;
                    $scope.pasResu=true;
                }
            }
            else{
                $scope.resu=false;
                $scope.pasResu=false;
                $scope.erreur=true;
            }
        })  
    }
    $scope.listeRecherchePro=function(){
        var blem = document.getElementById('bleme').options[document.getElementById('bleme').selectedIndex].value;
        var region = document.getElementById('region').options[document.getElementById('region').selectedIndex].value;
        var stat = document.getElementById('stat').options[document.getElementById('stat').selectedIndex].value;
        console.log(blem);console.log(region);console.log(stat);
        console.log('http://localhost:8080/listeRecherchePro/'+blem+'-'+region+'!'+stat);
        $http.get('http://localhost:8080/listeRecherchePro/'+blem+'-'+region+'!'+stat)
        .success(function(data){
            $scope.erreur=false;
            $scope.recherche="";
            if (data!='err'){
                if (data!=''){
                    $scope.listeProbleme=data;
                    $scope.resu=true;
                    $scope.pasResu=false;
                }
                else{
                    $scope.resu=false;
                    $scope.pasResu=true;
                }
            }
            else{
                $scope.resu=false;
                $scope.pasResu=false;
                $scope.erreur=true;
            }
        })  
    }

}

function utilisateur($scope,$http){
    $http.get('http://localhost:8080/listeUtilisateur')
    .success(function(data){
        $scope.listeUtilisateur=data;
    })
}
function controlUtilisateur($scope,$http){
    $http.get('http://localhost:8080/listeUtilisateur')
    .success(function(data){
        $scope.listeUtilisateur=data;
    })
}
function controlDelete($scope,$http){
    $scope.deleteSignalement=function(){
        // console.log("Ambony "+$idSignalement);
        $http.get('http://localhost:8080/deleteSignalement')
        .success(function(data){
            // console.log("Ambany "+$idSignalement);
            $scope.erreur=false;
            $scope.id="";
            if (data!='err'){
                if (data!=''){
                    $scope.listeProblemeRegion=data;
                    $scope.resu=true;
                    $scope.pasResu=false;
                }
                else{
                    $scope.resu=false;
                    $scope.pasResu=true;
                }
            }
            else{
                $scope.resu=false;
                $scope.pasResu=false;
                $scope.erreur=true;
            }
        })  
    }
}


/*function connexion($scope,$http){
    $scope.connexion=function($login,$mdp){
        $log=$scope.login+'°'+$scope.mdp;
        $http.get('http://localhost:8080/login/'+$log);
        .success(function(data){
            $scope.listeutilisateur=data;
        })
    }
}*/
    