#Ayden Masters
#COP2002.0M1
#February 9th, 2020
#Area and Perimiter
#This program uses input from the user to calculate the area and
#permiter of a rectangle

#Enter_Name = (input ("Enter your name:"))
#
#print("The Area and Perimiter Program")
#
#Enter_Length = int(input ("Please enter the length:"))
#Enter_Width = int(input ("Please enter the width:"))
#Show_Area = Enter_Length * Enter_Width
#Show_Perimiter = Enter_Length + Enter_Width
#
#print("Area =",Show_Area)
#print("Perimiter =",Show_Perimiter)
#print(Enter_Name + ", Thank you for using this program!")

# Python Program to Calculate Sum of Even Numbers or Odd Numbers from 1 to 50

#prompt the user for an integer between 1 and 50 and validate the entry
#check if the number is odd or even:
#a number is odd if number mod 2 = 1 
#a number is even if number mod 2 = 0
#sum and display the sum of all odd or even numbers, depending on user input
#allow user to repeat the program as often as desired
#Validate input to be an integer between 1 and 50(inclusive).

def funcGoAgain():
    yes = set(['yes','y', 'ye', ''])
    no = set(['no','n'])
    while True:

        choice = input("\n\nWould you like to try again? (y/n)")

        if choice in yes:
            funcSumCalc()
        elif choice in no:
            print("\n\nThank you for using my app! Have a nice day!")

            return False
        else:
            print("\n\nBad input please try again. Please input y/n!")


def funcSumCalc():

    Done = "false"
    Attempt = 1
    
    while Done == "false":

        if Attempt > 1:
            Message = " \n\nTry again. Your input must be between 1 and 50: "
        else:
             Message = "Enter a whole number between 1 and 50:"
        while True:

            try:
                maximum = int(input(Message))
            
                #maximum = int(input(Message))
                evenTotal = 0
                oddTotal = 0
                if (1 <= maximum <= 50):
                    
                    if (maximum % 2 == 0):
                        for evenNumber in range(1, maximum +1):
                            if (evenNumber % 2 == 0):
                                evenTotal += evenNumber
                        print("\n\nThe Sum of Even Numbers from 2 to {0} = {1}".format(evenNumber, evenTotal))

                    elif (maximum % 2 != 0):
                        for oddNumber in range (1, maximum +1):
                            if (oddNumber % 2 != 0):
                                oddTotal += oddNumber
                        print("\n\nThe Sum of Odd Numbers from 1 to {0} = {1}".format(oddNumber, oddTotal))

                        Done = "true"
                else:
                    Attempt += 1

            except ValueError:
                Attempt += 1
                Done = False
        
    

funcSumCalc()

funcGoAgain()
