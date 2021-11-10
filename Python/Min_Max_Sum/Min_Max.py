#!/bin/python3

#
# Complete the 'miniMaxSum' function below.
#
# The function accepts INTEGER_ARRAY arr as parameter.
#

def miniMaxSum(arr):
    # Write your code here
    arr = checkConstraints(arr)
    print(str(sum(arr) - max(arr)) + " " + str(sum(arr) - min(arr)))


def checkConstraints(arr):
    # remove any incorrect values from the array
    for num in arr:
        if num < 0 or num > pow(10, 9):
            arr.pop(arr.index(num))
    return arr


if __name__ == '__main__':

    arr = list(map(int, input().rstrip().split()))

    miniMaxSum(arr)
