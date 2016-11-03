===== Displaying the data =====

The GUI uses a Model View Controller design handle the fetching and displaying of data.
At it's creation, the view is passed the model, and it iterates through the model, getting data from the squares and constraints to create corresponding GUI elements and adding them to the board object in the view.

The only time the controller changes what data is shown on the view is when loading a game, where upon loading the save, the view removes its old grid and creates a new one based on the loaded game.

===== Editing the data ======

As the user inputs values into the board, no action is immediately taken. Instead, whenever the program needs to 'commit' the entered data (IE, when checking the status of the board, or saving the game), the controller calls a method 'updateModel()' which iterates through the current entries in the view, checks they are valid, removes any whitespace and then sets them correspondingly in the model, before doing whatever else was asked of it.

===== Checking legality and reporting problems ======

At any time the user can click the 'check' button which checks the legality of the board. This calls the getProblems() method in the model, which returns a string of any problems, or a string explaining the board state is legal. This is printed in a dialog box onscreen.

===== Optional Extras =====

Saving and loading are supported.