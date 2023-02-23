Image Usage: This is my shoe and my image. I took the picture and give myself the rights to use it.

Design overview:
* We have a model which does all the actual modifications to an image
* Brighten, darken, flip, visualizing components, etc.
* 2 different kinds of views
   * Text view - relaying messages to the user in text form
   * GUI view - A GUI with buttons for making edits to the image and displaying the image in live time as well as a histogram of the color components
* 2 different kinds of controllers
   * Controller for running the program in interactive mode/ script mode.
      * Takes inputs from the user in the form of text either from keyboard or a script and calls necessary methods in the model and view
   * Controller for running the program in GUI mode.
      * Takes inputs as button presses from the GUI and executes all the available features of the program.
Program capabilities:
* The program can load and save ppm images as well as conventional image types such as .jpg, .png, and bmp
* The program has the ability to blur, sharpen, brighten, darken, greyscale, and visualize-sepia. 
* It can take in script commands interactively or through a file given in the command line. 


Use:
* Open terminal and navigate to the folder with the jar file
* Use “java -jar Assignment6.jar” to run the GUI
* Use “java -jar Assignment6.jar -text” to enter interactive mode
* Use “java -jar Assignment6.jar -file file-path” to enter script mode


Changes we made:
* We made a features interface which is implemented by our new GUI controller
   * This has methods for executing all the capabilities of the program
   * These methods are used as action listeners in the GUI
   * Calls methods from the model/view to execute commands
* We made a GUIView which extends our old interface and extends JFrame
   * The constructor constructs a frame with 4 panels
      * A panel containing all the buttons for editing, loading, and saving an image
      * A panel of radio buttons to switch between loaded images
      * A panel displaying the current image being worked on
      * A panel displaying the histogram of the current image
   * The methods in the GUIView use input from the user as well as information from the controller to create the display.