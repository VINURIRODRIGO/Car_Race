# Formula 1 Championship Manager

This Java project is designed to manage a Formula 1 championship, allowing users to create, manage, and view statistics for drivers and teams participating in the championship. The project consists of classes and interfaces to handle drivers, teams, races, and overall championship management. Additionally, it provides a graphical user interface (GUI) built with Java Swing for a more intuitive user experience.

## Features

- Create, delete, and update drivers and teams participating in the Formula 1 championship.
- View detailed statistics for individual drivers, including their current points, race positions, and more.
- Display the Formula 1 Driver Table, ranking drivers based on their current points in the championship.
- Add new races with randomized race positions or probabilistic race outcomes based on starting positions.
- Save and resume the state of the program, including all entered information and race results.
- Graphical user interface (GUI) for ease of use and enhanced visualization of championship data.

## Classes and Interfaces

- `Driver`: An abstract class representing a Formula 1 driver, with subclasses like `Formula1Driver` implementing specific functionalities and statistics tracking.
- `Team`: Represents a team or car manufacturer associated with drivers.
- `Race`: Stores information about a race, including date played and positions achieved by drivers.
- `ChampionshipManager`: An interface defining methods for managing drivers, teams, and races in the championship.
- `Formula1ChampionshipManager`: A concrete implementation of `ChampionshipManager` handling Formula 1 championship management.

## Graphical User Interface (GUI)

The GUI provides the following functionalities:

- Display a table of all drivers participating in the championship, sorted by points.
- Sort the driver table by points won or by the largest number of first positions won in races.
- Generate random races with randomized positions or probabilistic outcomes based on starting positions.
- View a list of completed races, sorted by the date played.
- Search for all races in which a given driver participated, displaying detailed race information.

## Getting Started

To run the Formula 1 Championship Manager:

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Clone the repository to your local machine.
3. Compile the Java source files.
4. Run the main program file to start the application.
5. Follow the on-screen instructions or navigate through the GUI to manage the championship.

## Contributing

Contributions to the project are welcome! Feel free to fork the repository, make improvements, and submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify the code for your own purposes.

## Acknowledgments

- Special thanks to OpenAI for providing the platform to develop and showcase this project.
- Inspired by the excitement and passion of Formula 1 racing.

## Contact

For questions, suggestions, or support, please contact [your email or username].
