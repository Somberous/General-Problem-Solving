#!/bin/python3

import sys


def plusMinus(arr):

    positiveCount = 0
    negativeCount = 0
    zeroCount = 0
    arrCount = len(arr)

    numConstraintValidator(arr)
    for num in arr:
        if num == 0:
            zeroCount += 1
            continue
        elif num > 0:
            positiveCount += 1
            continue
        elif num < 0:
            negativeCount += 1
            continue

    print("Positive: " + getRatio(arrCount, positiveCount) + "\nNegative: " +
          getRatio(arrCount, negativeCount) + "\nZero: " + getRatio(arrCount, zeroCount))


def getRatio(arrCount, numCount):
    # Format the decimal to the 10^-8
    decimalLength = 8
    return str("{{:.{}f}}".format(decimalLength).format(numCount/arrCount))


def numConstraintValidator(arr):
    for num in arr:
        if (num < -100 or num > 100):
            print(
                "invalid input supplied to the application, please input numbers from -100 through 100")
            sys.exit()


def arrConstraintValidator(num):
    if (num <= 0 or num > 100):
        print(
            "invalid input supplied to the application, please input numbers from 1 through 100")
        sys.exit()


if __name__ == '__main__':
    n = int(input("Array Length: ").strip())

    arrConstraintValidator(n)

    arr = list(map(int, input("Array values: ").rstrip().split()))

    plusMinus(arr)
