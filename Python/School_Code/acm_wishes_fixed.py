#!/usr/bin/env python3

# Ayden Masters
# COP2002.0M1
# April 4th, 2020
# Project 4 fixing a program
# This program uses input from the user to create a whishlist for the user and then calulate the sub total and the total with tax of the items entered


"""
Project Statement
The application shown has five errors. Find and fix the four errors so
that the program allows the user to input as many items and their
costs as desired and outputs a subtotal of the items and a total
cost including 6% sales tax.

Fill in a description of the errors you found and how you fixed them below:
1. Goobye function was missing a " : " at the end of the definition statement.
2. Sub was needed in the get_tax functions parameters to pass the value of "sub" to the function.
3. Changed this value to .06 so it would calclulate 6% sales tax
4. Had to pass the sub value set in main to the get_item function
5. The while loop for the get_item functions parameters prevent it from ever entering the loop because the "and" parameter would never be met so it would just exit the program.
"""

def display_welcome():
    print("This program will create a wish list for the user.")
    print("Enter as many items as you like, with their costs")
    print("and the program will calculate your total, before and")
    print("after 6% tax.")

def get_item(sub):
    repeat = "y"
    while repeat.lower() == "y": # Error 5
        print("Enter an item? y/n ")
        repeat = input()
        if repeat.lower() == "y":
            print("Enter the name of your item: ")
            item_name = input()
            print("Enter the cost of this item: ")
            item_cost = float(input())
            item_cost = validate(item_cost)
            print("Item: ", item_name, " for $ ", round(item_cost, 2))
            sub = sub + item_cost
        else:
            print("Your subtotal is $", round(sub, 2))
            get_tax(sub)
            break      
                   
def validate(item_cost):
    while item_cost <= 0:
        print("Enter a valid cost for this item: ")
        item_cost = float(input())
    return item_cost

def get_tax(sub):# Error 2
    tax = sub * .06 # Error 3
    total_cost = sub + tax
    print("Your total cost, including 6% tax, is $", round(total_cost,2))
    goodbye()

def goodbye(): # Error 1
    print("Hope you get everything you wish for!")
    print("Goodbye!")

def main():
    display_welcome()
    sub = 0
    get_item(sub) # Error 4
    
# if started as the main module, call the main function
if __name__ == "__main__":
    main()

