# Importing time to get the timout feature for ending statement
import time
# import sys to exit the program
import sys
# Setting debug as a global varible to turn off my debugging with a boolean value globally
global debug
debug = False
# Setting the array as a global variable so in the input loop the array is not reset to an empty array
global houses
houses = []

# Simple function for a welcome statement into the app
def funcWelcomeStatement():
    # Debugging
    if debug == True:
        print("Starting the welcome message function")
    # Welcome statement
    print("Welcome to the Real estate values app.\n")
    #Calling the function for user input
    funcUserInput()

# Function for asking the user if they want to reuse the application
def funcGoAgain():
    # Debugging
    if debug == True:
        print("Starting the recurse function")
    # Setting the possible acceptable values a user can enter
    yes = set(['yes','y', 'ye', ''])
    no = set(['no','n'])

    # loop to make sure that the user is prompted everytime
    # To see if they want to keep using the program
    Done = False
    while Done == False:
        # Prompts the user for an input of y/n
        choice = input("\nWould you like to try other house values? (y/n)")

        # If the user types anything related to yes it will recall the input function
        if choice in yes:
            # Clears the array to allow new values
            houses.clear()
            # Calls the input function
            funcUserInput()

        # If the user types anything related to no it will stop the application
        elif choice in no:
            print("\nThank you for using my app! Have a nice day!\n\n")
            #Program waits 5 seconds before exiting
            time.sleep(5)
            break

        # Error Handling so the user can not break the program with bad input
        else:
            print("\nBad input please try again. Please input (y/n)!")
    sys.exit()

# Function to get the median price for the house prices enetered
def funcMedianPrice(prices):
    # Debugging
    if debug == True:    
        print("Starting median function")
    
    # Sorts the values in the array
    sortedPrices = sorted(prices)
    # Counts the number of values in the array
    pricesLength = len(prices)
    # Sets a value to determine if the array has a positive
    # Or negative amount of values within it
    index = (pricesLength - 1) // 2
    
    if (pricesLength % 2):
        # Debugging
        if debug == True:
            print("The array is odd... Returning the median")
        # Calculates the median for odd amount of array values  
        oddHomes = sortedPrices[index]
        print("The median value is ${:0,.2f}".format(float(oddHomes)))
    else:
        # Debugging
        if debug == True:
            print("The array is even... Returning the median")
        # Calculates the median for even amount of array values
        evenHomes = (sortedPrices[index] + sortedPrices[index + 1])/2.0
        print("\nThe median sale price is ${:0,.2f}".format(float(evenHomes)))

# Function to get the average price for the house prices entered
def funcAveragePrice(prices):
    # Debugging
    if debug == True:
        print("Starting the average function")
        # Calculates the average of the values in the array
    averge = sum(prices)/len(prices)
    print("The average sale price is ${:0,.2f}".format(float(averge)))

# Function to get the maximum price for the house prices entered
def funcMaximumPrice(prices):
    # Debugging
    if debug == True:
        print("Starting the maximum function")
    # Gets the largest value stored in the array
    maximum = max(prices)
    print("The maximum sale price is ${:0,.2f}".format(float(maximum)))

# Function to get the minimum price for the house prices entered
def funcMinimumPrice(prices):
    if debug == True:
        print("Starting the minimum function")
    minimum = min(prices)
    print("The minimum sale price is ${:0,.2f}".format(float(minimum)))

# Function to get input from the user
def funcUserInput():
    # Setting the value of our looping condition
    done = "false"
    # Iterative value
    attempt = 1
    # Start of the while loop for the try/except statements
    while done == "false":
        # Debugging
        if debug == True:
            print("Starting the outer loop")
        try:
            # Start of the while loop to get house prices from the user
            while True:
                # Debugging
                if debug == True:
                    print("Starting the inner loop")
                    # Defines how we add values to the loop with i from user input
                i =float(input("\nEnter cost of one home or -99 to quit: "))
                houses.append(i)

                # preventing the division by 0 in the median function by disallowing the user to 
                # Input -99 as their first value to exit the program
                if i == -99 and attempt == 1:
                    if debug == True:
                        print("User input -99 on first entry")
                    print("\nPlease enter at least one house value before attempting to exit!")
                    houses.remove(i)
                
                # Itteration so that -99 only fails the application if it was the first value
                elif i > 0:
                    attempt += 1

                # End condition for the prompt to the user
                elif i == -99:
                    # Removes the -99 value the user enterted to trip this if statement
                    houses.remove(i)
                    print("\nThese are the prices of homes in your area that you entered: ")
                    print("\n{0}".format(houses))
                    # Stopping both loops
                    done = "true"
                    break

                # Preventing negative input
                elif i < 0:
                    print("\nPlease do not input negative numbers!")
                    houses.remove(i)
                    
        # Error Handling so the user can not break the program with bad input
        except:
            print("\nPlease enter a valid house price!")
    funcForgottenValues()

# Function to ensure that the user did not forget any house prices
def funcForgottenValues():
    # Debugging
    if debug == True:
        print("Asking to confirm they input all values")
    # Setting the possible acceptable values a user can enter
    yes = set(['yes','y', 'ye', ''])
    no = set(['no','n'])

    # loop to make sure that the user is prompted everytime
    # To ensure they have input all of the data they want to calculate
    done = "false"
    while done == "false":
        # Prompts the user for an input of y/n
        choice = input("\nDid you forget any values and or want to add more values? (y/n)")

        # If the user types anything related to yes it will recall the input function
        # It will keep all previous values input by the user
        if choice in yes:
            funcUserInput()

        # If the user types anything related to no it will then send the values to the Calculation functions
        elif choice in no:
            done = "true"
        
        # Error Handling so the user can not break the program with bad input
        else:
            print("\nBad input please try again. Please input (y/n)!")
    funcMain()

# Main function where we call all calulation functions and the ending choice function
def funcMain():
    funcMedianPrice(houses)
    funcAveragePrice(houses)
    funcMinimumPrice(houses)
    funcMaximumPrice(houses)
    funcGoAgain()

# Start the program by calling the greeting function
funcWelcomeStatement()