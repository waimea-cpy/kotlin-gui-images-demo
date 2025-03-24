/**
 * ===============================================================
 * Kotlin GUI Image Demo
 * ===============================================================
 *
 * This demo shows
 */

import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*


/**
 * Launch the application
 */
fun main() {
    FlatDarkLaf.setup()     // Flat, dark look-and-feel
    val app = App()         // Create the app model
    MainWindow(app)         // Create and show the UI, using the app model
}


/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */
class App() {
    // Data fields
    val imageFiles = listOf(
        "colour.png",
        "face.png",
        "pizza.png",
        "finn.png",
        "jake.png",
        "invader.png",
        "cpy.png",
        "cop.png")
    var currentImageFile = imageFiles.random()

    // Application logic functions
    fun pickRandomImage() {
        currentImageFile = imageFiles.random()
    }
}


/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // Fields to hold the UI elements
    private lateinit var imageLabel: JLabel
    private lateinit var randomButton: JButton

    /**
     * Configure the UI and display it
     */
    init {
        configureWindow()               // Configure the window
        addControls()                   // Build the UI

        setLocationRelativeTo(null)     // Centre the window
        isVisible = true                // Make it visible

        updateView()                    // Initialise view with model data
    }

    /**
     * Configure the main window
     */
    private fun configureWindow() {
        title = "Kotlin Swing GUI Image Demo"
        contentPane.preferredSize = Dimension(350, 425)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 24)

        imageLabel = JLabel()
        imageLabel.bounds = Rectangle(25, 25, 300, 300)
        imageLabel.font = baseFont
        add(imageLabel)

        randomButton = JButton("Random Image")
        randomButton.bounds = Rectangle(25, 350, 300, 50)
        randomButton.font = baseFont
        randomButton.addActionListener(this)     // Handle any clicks
        add(randomButton)
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
        // Gte the image file
        var image = ImageIcon("src/images/" + app.currentImageFile).image
        // Resize it to fit the label
        image = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH)
        // And show it
        imageLabel.icon = ImageIcon(image)
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            randomButton -> {
                app.pickRandomImage()
                updateView()
            }
        }
    }

}

