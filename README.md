# RType (September 2013)

![](https://github.com/jled7/RType/raw/master/images/start.png)

## 1.- Description

1 - Start the game with a **welcome screen** in which you can select the game mode (EASY, NORMAL, COMPLICATED, IMPOSSIBLE) and start playing.

2 - The game consists of a single **level** , in which the **player controls the friendly ship**. You only have **one life** to take down the horde of alien ships. That they will be presented in **different groups** depending on the **difficulty selected** at the beginning. EASY = 10, NORMAL = 15, COMPLICATED=20 , IMPOSSIBLE=30.

3 - Allied ship moves **up** (Key **Q** ), **down** ( Key **A** ), **left** (Key **O** ) and **right** (Key **P** ), and shoot with the SPACE key. It **moves** across the **entire screen** , detecting that does not go out of the **limits**. The **shot** made by the ship is **continuous**, it is not necessary to wait for missile leaves the screen, and **there's only one type**.

4 - The **alien ships** are controlled by the **computer** and **don't shoot**. There are **two kinds** of ships differentiated by their movement. One type of ship only moves in the X axis and another in the X and Y.

5 -The **speed** of these is based on the **selected difficulty,** and once they reach the left of the screen reappear on the right side.

- Two types of collisions are detected:
    - The collision between the **allied ship** and an **alien ship**.
    - The collision between a **missile** and an **alien ship**.

6 – There is no need to implement relief or any collision with it. Once all destroyed the aliens a congratulatory message appears and the initial menu would be shown again.


## 2. - Design

I wanted to create an inheritance relationship on a general object that we wanted to represent in a position and update it as desired or as described in the object's code. Those objects will be an Entity, that is, the ship will be an entity, the shot will be an entity, even the alien will be an entity, and the methods of those entities are defined in the **Entity Interface.**

This interface only adds the methods to be able to know its position, visibility, size or even change them. It is the **Drawable Interface** which adds a method to be able to draw on the screen.

![](https://github.com/jled7/RType/raw/master/images/interfaces.png)

Based on these interfaces, two abstract classes are created, which are the ones on which almost all of the draft. **Map** and **Sprite**. As its name makes clear, the classes **Sprite** are the objects that are in motion, interacting with the rest of the Sprite-s, a **Sprite** is a **Drawable Entity** ( **Drawable** ), the difference with the **Map** is that the **Sprite** is reduced, it has a few pixels high and others low, but the **Map** class occupies the entire screen, either storing a Menu or the stars that go to the bottom. The movement of the **Map** class is also only in the X axis.

All Sprites have their position in X and Y coordinates, which move according to the keys pulsed or the programmed movement. They are assigned a height and a width to be able to detect collisions between them, although we only take into account those that occur with ships aliens.
At first, my first idea was to create a virtual board, and move the ship by squares, although this would give me more control over what I programmed, it reduced the dynamism of the game, the R-Type is a fast game, adrenaline reflexes to dodge. And a vague collision detection(since the virtual board should have tiny squares and have many of them, so that the collisions would be optimal (and it would also be a bit slow to have so many)) would spoil the beauty and good of this game, get rid of obstacles that you thought were above you.

That's why I also chose to reduce his height when he was moving, I explain. The ship implements an **Animated** interface that gives you the methods with which it is possible to animate the Sprite, with this and the class **Animation** which is the one that contains the animation, it is achieved that the ship animate something like turning on your side, at which point your height decreases, to fit the limits of the graph and that the collision is more precise.

![](https://github.com/jled7/RType/raw/master/images/collision.png)

All graphics used in the **Sprite** class are taken from a **SpriteMap** or a single Image. What the **SpriteMap** does is from an Image where there are images, divide into images by column and row, so you can use them later. For example the **SpriteMap** used in this game has been East:

![](https://github.com/jled7/RType/raw/master/images/spritemap.png)

The **SpriteMap** class parses the height and width of the image and divides them based on the height and width that we want to use, which is that of the **Sprite** , we use 32x32 px.

The values ​​that we use to divide the **SpriteMap** are taken from a public class that only contains **constants** ,which we use throughout the game, with data for example height and width of the window, the speed of the ship, the speed of the game clock, or even the percentage of stars in the sky you want.

The reason we use the **Constants** class is because that way we avoid having to alter those values ​​in more than one place, for example if we want to change the speed of the player's ship, in Instead of changing that single value in all the methods that use it, we change the constant static.

Instead of using the constants, you could make it load it from some file (eg: game.dat) and I was even thinking of implementing it like this, along with the "frames" of the animations of the sprites. But any change, whether it was simply changing the clock speed, if needed to run the application, you would have to edit the file.

The entire game is handled from the keyboard. Either from the welcome menu where you have to choose the difficulty or in-game. For that I provide a public class **KBListener** that extends the **KeyAdapter** class, which is the **KeyListener** where all keystrokes are "listened" that interest us, this class is public and only contains static methods, that is, we can "listen" from any class.

![](https://github.com/jled7/RType/raw/master/images/game.png)

Also in menu presses, I have added a delay of a clock time, so that it is not requests will overlap. For example in the welcome screen that asks you to press space to start, when you press space and the game mode menu appears, the game would start with the mode that is selected by default, since when seeing that the space key is pressed, it interprets it as if I had to compute it. Another simulated delay is in the shot so as not to fire 10 missiles at once.
The movement of the ships wanted to be fluid (another reason why I discouraged the use of a virtual board with squares). Many times when playing these types of games in which the speed and attention are very important, the keyboard has to respond as we would like. If the player doesn't want the ship to move it's very easy, he stops pressing the buttons, but the speed of the game forces us to be in constant movement and for example if you are pressing to up and you want to dodge an alien you see coming. You press down, but you're still pressing above, what could happen is: that the ship stops (you are indicating two different speeds, one up and one down) or continue up without paying attention to the instructions. I wanted to avoid that behavior that could make the gameplay much worse, adapting to the combined keystrokes (up and down, left and right) and the ship's original address before it was pressed. 
Here's an image of the game:
