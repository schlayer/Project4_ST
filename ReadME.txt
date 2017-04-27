Seth Schaffer	|	Project 4	|	Did Not Collaborate

My ".class" main class files are located at: Project4_ST\bin\lc\example\
LogisticClassifierTest.class: Logistic linear classifier
PerceptronClassifierTest.class: Hard threshold linear classifier

My ".java" main class files are located at: Project4_ST\src\lc\example\
LogisticClassifierTest.java: Logistic linear classifier
PerceptronClassifierTest.java: Hard threshold linear classifier


To run: use the commands as originally written in Fergusson's code. 
EX: 
LogisticClassifierTest.java earthquake-noisy.data.txt 12000 0.5
This will navigate into the example folder and find the requested filename, then run 12000 steps with alpha = 0.5.

For either one, alpha < 0 will utilize a decaying learning rate. If you try to enter a number of steps under 1100, 
your entry will be set to 1100, as otherwise the graph will not display properly and accuracy will be awful anyway. 

I adjusted the output graphs to have gridlines to better demonstrate the learning curve. 
Accuracy scores are scaled such that instead of the default 1px = 1 step, it just takes the 1000px width and 
distributes the scores across that at even step intervals. Each major gridline is 10%, each minor gridline is 2%. 


I didn't write code for decision trees or neural networks so please do not attempt to test them. 

 

