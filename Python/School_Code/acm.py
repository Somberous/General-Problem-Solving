# Ayden Masters
# COP2002.0M1
# March 15, 2020
# This program gets the Sum of Odd or Even number 
# between 1 and 50 (inclusive) that the user enters
# Sum Calculator

# Function to ask the user if they want to repeat the program
import time

def funcGoAgain():
    # Setting the possible acceptable values a user can enter
    yes = set(['yes','y', 'ye', ''])
    no = set(['no','n'])

    # loop to make sure that the user is prompted everytime
    # On if they want to keep using the program
    while True:

        # Prompts the user for an input of y/n
        choice = input("\n\nWould you like to try again? (y/n)")

        # If the user types anything related to yes it will recall the calculation function
        if choice in yes:
            funcSumCalc()

        # If the user types anything related to no it will stop the application
        elif choice in no:
            print("\n\nThank you for using my app! Have a nice day!\n\n")
            time.sleep(5)
            return False

        # Just some error handling to prevent bad input
        else:
            print("\n\nBad input please try again. Please input y/n!")

# This function does all of the calcultion for the app

def funcSumCalc():
    # Defining the properties for the loop
    Done = "false"
    # This determines what prompt the user will get when asked for a number
    Attempt = 1
    
    while Done == "false":
            # Defines the prompts that are possible for the user to see
            if Attempt > 1:
                Message = " \n\nTry again. Your input must be a number between 1 and 50: "
            else:
                Message = "Enter a whole number between 1 and 50:"
                        
            try: 
                
                # Prompts the user for a number between 1 and 50
                maximum = int(input(Message))
                # Sets the value of the variable which will keep track of the total
                evenTotal = 0
                oddTotal = 0

            except:
                Attempt +=1

                # Skip the rest of the code, and continue with the looping
                continue
            
            # First if statement to make sure the number is bettween 1 and 50
            if (1 <= maximum <= 50):
                # Checks to see if the number is even if so it will calculate the sum of even numbers
                if (maximum % 2 == 0):
                    for evenNumber in range(1, maximum +1):
                        if (evenNumber % 2 == 0):
                            evenTotal += evenNumber
                    print("\n\nThe Sum of Even Numbers from 2 to {0} = {1}".format(evenNumber, evenTotal))

                # If it was not an even number it checks to see if it is an odd number and then calculate the sum
                elif (maximum % 2 != 0):
                    for oddNumber in range (1, maximum +1):
                        if (oddNumber % 2 != 0):
                            oddTotal += oddNumber
                    print("\n\nThe Sum of Odd Numbers from 1 to {0} = {1}".format(oddNumber, oddTotal))
                Done = "true"
                # Error handling to prevent incorrect input by incrementing Attempt
            else:
                Attempt += 1
        
#Calling the functions   
funcSumCalc()

funcGoAgain()
