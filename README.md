# CaveFinderSystem
 
Description:
The Caverns Routing Application is a computer program designed to find the shortest route through a series of small underground caverns connected by straight tunnels. The program takes as input a cavern map file in the .cav format and outputs the shortest route in a solution file with a .csn extension. The program aims to efficiently find the shortest route from the first cavern to the last cavern in the input map.

Input Format:
The cavern map files (.cav) are text files with the following format:

The first integer specifies the number of caverns (N).
The next N*2 integers provide the coordinates of each cavern in (x, y) format.
The final N*N integers indicate the connectivity of the tunnels, where 1 means connected, and 0 means not connected. The connectivity matrix is given in the order specified in the document.
Output Format:
The solution files (.csn) contain a series of integers separated by spaces, denoting the order of visiting the caverns to find the shortest route. The route always starts at cavern 1 and ends at the last cavern specified in the input map.

Program Usage:
The program is designed to run on the Windows command line. It should be executed with one parameter, which is the name of the cave map file (without the .cav extension). For example, if the parameter is "banana," the program will read from "banana.cav" and write the solution to "banana.csn" in the current folder.
