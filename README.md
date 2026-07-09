# Shopping List Manager

This is a simple Java console application that I built to practice the basics of object-oriented programming (OOP) and file handling (File I/O) in Java.

## Features:

- Add items to the shopping list
- Remove items from the list
- View the list
- Save the list to a text file
- Load the list from a text file

## What I learned:

- The basics of Java classes and objects
- Working with `ArrayList`
- Reading and writing files (`FileReader`, `FileWriter`)
- Error handling with `try-catch`
- Menu-driven console applications using loops (`while`) and
  branching (`switch-case`)

## Technologies used:

- Java (console application)
- File storage using a simple CSV format (`Name,Quantity`)

## Project structure:

```
ShoppingListManager/
├── Main.java            (the complete program code)
├── shopping_list.txt    (created automatically on the first save)
└── README.md            (this file)
```

## How to run the program:

1. Install the Java JDK
2. Compile Main.java: `javac Main.java`
3. Run the program: `java Main`

## Example of a saved file (`shopping_list.txt`):

```
Milk,2
Bread,1
Eggs,12
```
