global debug
debug = False

def funcMedianPrice(prices):
    if debug == True:    
        print("Starting median function")

    sortedPrices = sorted(prices)
    pricesLength = len(prices)
    index = (pricesLength - 1) // 2
    
    if (pricesLength % 2):
        if debug == True:
            print("The array is odd... Returning the median")  
        oddHomes = sortedPrices[index]
        print("The median value is ${0}".format(oddHomes))
    else:
        if debug == True:
            print("The array is even... Returning the median")
        evenHomes = (sortedPrices[index] + sortedPrices[index + 1])/2.0
        print("The median sale price is ${0}".format(evenHomes))

def funcAveragePrice(prices):
    if debug == True:
        print("Starting the average function")
    averge = sum(prices)/len(prices)
    print("The average sale price is ${0}".format(averge))

def funcMaximumPrice(prices):
    if debug == True:
        print("Starting the maximum function")
    maximum = max(prices)
    print("The maximum sale price is ${0}".format(maximum))

def funcMinimumPrice(prices):
    if debug == True:
        print("Starting the minimum function")
    minimum = min(prices)
    print("The minimum sale price is ${0}".format(minimum))

def funcMain():
    Done = "false"
    houses = []
    while Done == "false":
        if debug == True:
            print("Starting the outer loop")
        try:
            while True:
                if debug == True:
                    print("Starting the inner loop")
                i =int(input("Enter cost of one home or -99 to quit: "))
                houses.append(i)

                if i == -99:
                    houses.remove(i)
                    print("These are the prices of homes in your area that you entered: ")
                    print("{0}".format(houses))
                    Done = "true"
                    break
        except:
            print("Please enter a valid number!")
            Done = "false"

    funcMedianPrice(houses)
    funcAveragePrice(houses)
    funcMinimumPrice(houses)
    funcMaximumPrice(houses)

funcMain()