## Image Processing USEME ##
List of supported commands for GUI:
- Click "Exit" button to exit at any time
- **Load**
  - Click the "Open File Button" and choose an image
  - Enter the reference name in the "Enter image name here" box
  - Click "Load Button"
  - Image and histograms are displayed in boxes below
- **Commands**
  - Select image to modify from "Image Selector" drop-down menu
  - Click "Modifier Button" and select modification/filter
    - Note: for Brighten command please input increment in "Enter increment here" box
  - Enter a name for the new image in the "Enter new image name here" box
  - Click "Do Mod Button"
  - New image and histograms are displayed
- **Save**
  - Select image you want to save from the "Image Selector" drop-down menu
  - Click "Save" button to save to computer in file path you are able to select

List of supported text commands:
- **Load**
  - Load takes in the name of the file (or full filepath) and the user-defined reference name
  in that order
  - example: "load sampleppm.ppm 3by3"
- **Save**
  - Save takes in the name of the new file and extension the user wants to be created 
  and the reference name for the file in that order
  - example: "save sepiacat.png sepiacat"
- **Brighten**
  - Brighten takes in the name of the file loaded in, the new name for the brightened
  image, and the increment to brighten or darken by positive for brighter, negative for darker in
  that order
  - example: "brighten balloon darkerballoon -75"
- **Flip**
  - Flip takes in either the keyword horizontal or vertical, the name of the file, and the new name
  for the flipped image in that order
  - example: "flip vertical field verticalfield"
- **Component**
  - Component takes in the name of the file, the new name for the image and an enum which is one of
    (red, green, blue, value, intensity, luma) in that order
  - example: "component balloon bluegrayballoon blue"
- **Blur**
  - Blur takes in the name of the file and the new name of the blurred image in that order
  - example: "blur 3by3 blur3by3"
- **Sharpen**
  - Sharpen takes in the name of the file and the new name of the sharpened image in that order
  - example: "sharpen cat sharpencat"
- **Grayscale**
  - Grayscale takes in the name of the file and the new name of the grayscaled image in that order
  - example: "grayscale field grayfield"
- **Sepia**
  - Sepia takes in the name of the file and the new name of the sepia image in that order
  - example: "sepia balloon sepiaballoon"