# Ayden Masters
# COP2002.0M1
# April 25th, 2020
# Project 6 calculating interest
# This program will calculate the interest amount 
# For a given loan amount

# Debug toggle
global debug
debug = False
# Time module to get the timout feature for ending statement
import time
# Using sys to exit the program when a user inputs "n"
import sys
# Using os to clear the console before the print statement.
from os import system, name
# Using math so that we can its functions for rounding
import math
# Using decimal module for decimals
import decimal

# Welcome statement
def funcWelcome():
    # Debugging
    if debug == True:
        print("Starting the program\n")

    # Welcome statements
    print("\n")
    print("This program will calculate the interest of a specified loan amount.")
    
    # Calling the main function
    funcMain()

# RECURSION!
def funcRecurse():
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
        choice = input("\nWould you like to try another value? (y/n)")

        # If the user types anything related to yes it will recall the input function
        if choice in yes:
            # Calls the input function
            funcMain()

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

# Getting the loan to a usable state for calculation
def funcLoan():
    # Debugging
    if debug == True:
        print("Asking the user for loan\n")

    # User input
    global loanAmount
    loanAmount = str(input("\nPlease enter your loan amount: "))

    # Debugging
    if debug == True:
        print("\nLoan amount replacements")

    # Replacing invalid characters
    loanAmount = loanAmount.replace('$','')
    loanAmount = loanAmount.replace(',','')
    # Converting everything to lowercase
    loanAmount = loanAmount.lower()

    # Checking if loan amount has k at the end
    validate = loanAmount.__contains__('k')

    # Final validation and then multiply the value by 1000
    if validate == True:
        loanAmount = loanAmount.replace('k','')
        loanAmount = float(loanAmount)
        loanAmount = loanAmount * 1000

        # Debugging
        if debug == True:
            print("Validated input {0}".format(loanAmount))

    # Changing value to float for calculation
    loanAmount = float(loanAmount)
    # Validate inside function rather then an external function
    if loanAmount < 0:
        print("\nPlease input a postive number!")
    elif loanAmount > 0:
        return

# Getting the interest to a usable state for calculation
def funcInterest():
    if debug == True:
        print("Asking the user for interest\n")

    # Input statements as strings to accept the input of a "$" or "%" in the input fields.

    global interestAmount
    interestAmount = str(input("Please enter your interest rate: "))
    # Replcaing invalid characters
    interestAmount = interestAmount.replace('%','')
    # Changing value to decimal because I hate myself
    interestAmount = float(interestAmount)

    # Rounding to the nearest thousandth if a user inputs more than three decimal places
    interestAmount = funcRound(interestAmount,3)

# Easy global function for rounding
def funcRound(interest,decimals = 0):
    # Simple rounding
    multiplier = 10 ** decimals
    return math.floor(interest * multiplier + 0.5) / multiplier

# Calculating the interest amount
def funcMath():
    global interestValue
    interestValue = loanAmount * (interestAmount/100)
    funcRound(interestValue,2)
    
# Printing the output
def funcPrint():
    # Print statements
    print("\n")
    print("Loan amount:            ${:0,.2f}".format(loanAmount))
    print("Interest rate:               {:0,.3f}%".format(interestAmount))
    print("Interest amount:          ${:0,.2f}".format(interestValue))

# Simple way to clear console and keep the output neat with os module
def funcClear(): 
  
    # for windows 
    if name == 'nt': 
        _ = system('cls') 
  
    # for mac and linux(here, os.name is 'posix') 
    else: 
        _ = system('clear')

# Calling all the functions in order
def funcMain():
    funcLoan()
    funcInterest()
    funcMath()
    funcClear()
    funcPrint()
    funcRecurse()

funcWelcome()