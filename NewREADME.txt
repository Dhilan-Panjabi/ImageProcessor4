We were able to implement the mosaic command correctly - it works in the GUI and in the script command line.

Mosaic image implementation: Y
Script command to mosaic image: Y
Mosaic image from GUI: Y

The way we implemented it to work was first we made a new command class which implements their current ICommand class so we can make use of the apply function and toString function.
The apply function like how they did all of their command was to take in the model and then apply the mosaic command to the model given the number of seeds. We created four private functions
the mosaic image which takes in the seeds and the model within this function the mosaic image is created by using the getRandomSeeds which finds random seeds in the image and then the getClosestSeeds
finds those closest seeds and gets the color to add to the color map. The mosaic image function then gets the pixel at the random seed and changes the color to the closest seed color.

To implement this into the GUI we made use of the Controller class which implements the IController class. Within the method we put the mosaic command into the array of options
once this was done we added an if statement that if the selected command is mosaic there would be an option for the user to enter the number of seeds. Then in the same method in their
command design pattern we added a case for mosaic which gets the number of seeds and then runs the mosaic command on the model. If the integer is invalid it will throw an exception.

To implement this into the script command line we made use of the ImageController class which implements IController. The method run takes in the command line arguments from the user and then checks the string to see the
command name and see whether it would be one of the given command names that are in the knownCommands map. Hence, what we did was add the mosaic command into the knownCommands map which checks the next string, next string and next int
this will return the name of the image, the destination of the image, and the number of seeds which will then be the parameters to run the mosaic command on the loaded image.

Script:
Mosaicking ImageName, Destination Name, Number of Seeds

Example Script:
Mosaicking cat image res/cat.png 1000