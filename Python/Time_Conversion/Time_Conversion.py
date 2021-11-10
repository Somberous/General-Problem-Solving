#!/bin/python3

import os

#
# Complete the 'timeConversion' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
# The goal in my mind was to not use the built in libraries for time within python
#


def timeConversion(s):
    ToD = s.__contains__('PM')
    s = s.strip('AM,PM')

    hour = int(s.split(':', 1)[0])
    minutes = int(s.split(':', 2)[1])
    seconds = int(s.split(':', 3)[2])

    if hour < 12 and ToD:
        hour += 12
    elif hour >= 12 and not ToD:
        hour -= 12

    return str("{:02d}".format(hour))+':'+str("{:02d}".format(minutes))+':'+str("{:02d}".format(seconds))


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = timeConversion(s)

    fptr.write(result + '\n')

    fptr.close()
