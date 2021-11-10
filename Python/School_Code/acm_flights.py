# Ayden Masters
# COP2002.0M1
# April 14th, 2020
# Project 5 Fuel and Time Calculation
# This program will calculate the amount of time 
# and fuel for a 1980 Cessna 172N to fly a specified distance.

# Debug toggle
global debug
debug = False
# Date time module to help display travel time
import datetime
# Time module to get the timout feature for ending statement
import time
# Using sys to exit the program when a user inputs "n"
import sys
# Using os to clear the console before the print statement.
from os import system, name
# Using math so that we can use ceil to always round up
import math

# Welcome
def funcWelcome():
    # Debugging
    if debug == True:
        print("Starting the program\n\n")

    # Welcome statements
    print("Aircraft Fuel Calculator\n")
    print("This program will calculate the amount of time and fuel for a 1980 Cessna 172N to fly a specified distance.")
    
    # Calling the main function
    funcMain()

# Getting input from the user
def funcInput():
    # Debugging
    if debug == True:
        print("Asking the user for input\n")
    
    global miles
    # Prompting the user for input
    try:
        miles = float(input("\nPlease input the distance in nautical miles you will travel: "))
        # Validating the users input
        funcValidate(miles)
    # Error handling
    except:
        print("\nPlease enter a valid number!")
        funcInput()
    
# Validating user input
def funcValidate(miles):
    # Ensuring the number is positive
    if miles < 0:
        print("\nPlease input a positive number!")
        funcInput()
    # Call flight time calculation
    elif miles >= 0:
        funcTime(miles)

# Calculate the Flight time       
def funcTime(miles):
    global flightTime
    flightTime = miles/120

# Adding 30 minutes to the flight time for when fuel calculation happens
def funcSafety(flightTime):
    # Debugging
    if debug == True:
        print("Adding 30 minutes to the flight time\n")

    # Adding 30 minutes to the flight time for fuel calculation
    global extraTime
    extraTime = flightTime + 0.50

# Calculating the amount of fuel the plane will need
def funcFuel(extraTime):
    # Debugging
    if debug == True:
        print("Calculating the required fuel\n")
    
    # Calculating the fuel amount
    global fuel
    fuel = extraTime * 8.4
    fuel = funcRound(fuel,1)

# Always rounding up for safety
def funcRound(fuel, decimals=0):
    # Always rounding the fuel amount up to the nearest 10th
    multiplier = 10 ** decimals
    return math.ceil(fuel * multiplier) / multiplier

# Printing all output the the console
def funcDisplay(miles,timeFormated,fuel):
    # All print statements to reduce clutter in other functions
    print("Distance in nautical miles: {0}".format(miles))
    print("Flight Time: {0}".format(timeFormated))
    print("Required Fuel: {0} gallons".format(fuel))

# Formating time
def funcFormat(flightTime):
    global timeFormated
    # Formating flightTime from floa to h:mm format
    timeFormated = '{0:2.0f} hour(s) {1:02.0f} minutes(s)'.format(*divmod(flightTime * 60, 60))

# Simple way to clear console and keep the output neat with os module
def funcClear(): 
  
    # for windows 
    if name == 'nt': 
        _ = system('cls') 
  
    # for mac and linux(here, os.name is 'posix') 
    else: 
        _ = system('clear')

# RECURSION 
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

# Keeping all called functions stored in main for easy recursion
def funcMain():
    # Calling all functions in order
    funcInput()
    funcSafety(flightTime)
    funcFuel(extraTime)
    funcFormat(flightTime)
    funcClear()
    funcDisplay(miles,timeFormated,fuel)
    funcRecurse()

# Starting the program with a welcome statement  
funcWelcome()