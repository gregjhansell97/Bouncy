# Bouncy

Bouncy is a game where a user controls a ball (playerBall). Every 5 seconds a new ball shoots out from playerBall, and bounces on the screen. When a ball hits playerBall, playerBall decreases in size. After a set number of hits, the game is over and everything is reset.

Bouncy follows the MVC design pattern. The model is comprised of various ball objects and a boundary. The Controller listens for user inputs (mouse or pad) and controls the playerBall from there. The View Controller uses a JPanel to display the balls on screen.
