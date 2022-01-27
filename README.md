# Fractals-using-recursion
Fractals (https://en.wikipedia.org/wiki/Fractal) are structures or visual phenomena that exhibit self- similarity at
every scale. It’s quite easy to generate visually pleasing Fractals using recursion. In this project, I implement five
Fractals: the Koch curve, Tree, the Sierpinski Triangle, Carpet, and once I tried to recreate with something I looked up
online.
Fractal.java is responsible for drawing the fractal shapes onto a Graphics2D object (which could be the screen or could
be an image). To see the visual results, open FractalGUI.java in the support folder, and run it. This is the Graphics
User Interface (GUI) that shows the fractals and provides basic controls such as changing the fractal type and number of
recursions.

<ol>
    <li><b>Koch Curve</b>
    <div>
        It begins with two 2D points p1 and p2. If this is the base case (indicated by recursion level 0), you simply
        draw a line connecting these two points. If this is not the base case, you need to compute the intermediate
        points p3, p4, p5 and recursively call drawKochCurve on the four subdivided segments (p1-p3, p3-p4, p4-p5,
        p5-p2).
        Point2D Class. The Fractal program uses Java’s Point2D class, which represents a point (x, y) in 2D. You can
        check the specification of this class here: https://docs.oracle.com/javase/8/docs/api/java/awt/geom/Point2D.html
    </div>
    <div>
        The Turtle Class helps one to compute the intermediate points on the Koch curve. 
        In Turtle.java, the constructor takes a starting point and a target point. 
        This places the turtle at the starting point, and setting its direction to go towards the target point. The move method moves
        the turtle for a given distance along its current direction; the getPosition method returns its current
        position; and the turnLeft, turnRight methods cause the turtle’s direction to change by a given amount of
        degrees.
    </div>
    <div>
        With the Turtle class, it’s easy to compute the intermediate points for the Koch curve. Specifically, you first
        create a new Turtle object with p1 as the starting point and p2 as the target point. You calculate the distance
        d between the two points. Then you let the turtle move a distance of d/3. Now use the turtle’s getPosition
        method to obtain its current location, and that’s p3. Next, call turnLeft(60) to let the turtle turn left by 60
        degrees, and move(d/3) again to move it to point p4. You can similarly find point p5. See the left picture below
        for illustration.
    </div>
    </li>

    <li><b>Tree</b>
    <div>
        Similar to before, I started with two 2D points p1 and p2. If this is the base case (level is 0), you simply draw
        a line connecting the two points. Otherwise, you need to compute the intermediate points p3, p4, p5, as show in
        the figure below. As before, d is the distance from p1 to p2; p3 is the point along p1-p2 but d/3 on the way,
        and this is where the tree starts to branch out. One branch turns 60 degrees to the left, reaching p4, and the
        distance between p3-p4 is 32 d. The other branch turns 15 degrees to the right, reaching p5, and the distance
        between p3-p5 is also 23 d. Given these, you can use the Turtle class to help you compute points p3, p4, p5, and
        then you should 1) draw a line from p1 to p3 2) recursively call drawTree on segments p3-p4 and p3-p5 respectively.
    </div>
    <div>
        In the draw() method, I have doubled the recursion level for the “tree” case. So in the FractalGUI, each tick of
        the slider increments the recursion level by two for the fractal tree. If you would rather see the level
        increments one by one, change max_recursion_level*2 to simply max_recursion_level in the draw() method.
    </div>
        </li>

    <li><b>Sierpinski Triangle</b>
    <div>
        The Sierpinski Triangle is a 2D fractal shape. I started with three points p1, p2, p3 that define an equilateral
        triangle. If this is the base case, you simply draw the triangle (by calling the drawTriangle method already
        provided to you). Otherwise, you split it into four equal sub-triangles (by finding the midpoints on each side
        of the triangle); remove the middle sub-triangle (i.e. don’t do recursion on the middle sub-triangle); and then
        apply recursion on each of the remaining three sub-triangles.
    </div>
        </li>

    <li><b>Sierpinski Carpet</b>
    <div>The Sierpinski Carpet is a 2D fractal shape similar to the Triangle. I started with a square defined by the
        lower-left corner point p and the side length a. If this is the base case, you simply call drawRectangle method
        to draw the square. Otherwise, you split it into 9 equal-sized sub-squares, and remove the middle sub-square (so
        it’s no longer part of the shape); then apply recursion on each of the remaining eight sub-squares.
    </div>
        </li>
    </ol>
