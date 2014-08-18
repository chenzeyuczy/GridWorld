ant
cp dist/GridWorldCode/gridworld.jar gridworld.jar
ant clean
java -cp gridworld.jar info.gridworld.maze.MazeBugRunner
