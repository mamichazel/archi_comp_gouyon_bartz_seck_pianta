Pour initialiser l'application, commencez par lancer la classe Main. Celle-ci va automatiquement créer la base de données ainsi que ses relations (en insérant un objet par entité).

Ensuite, pour ajouter, modifier, supprimer ou lire des données via des requêtes HTTP, ouvrez le fichier Test.http et sélectionnez "Run all requests". Les résultats de chaque requête s'affichent dans la console.

Pour tester les requêtes DELETE, veuillez vous référer aux commentaires situés en haut du fichier Test.http.

Enfin, une interface web a également été développée, permettant de visualiser certaines données grâce à des requêtes GET telles que l'affichage des vols, des passagers ou des bagages. Nous avons également fait un formulaire pour ajouter un vol (requete POST) en ManyToMany
Accédez-y via http://localhost:8080/

