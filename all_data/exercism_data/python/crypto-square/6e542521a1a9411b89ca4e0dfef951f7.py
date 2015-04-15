from math import sqrt, floor
import string

def isSquare(n):
    return (sqrt(n) - floor(sqrt(n)) == 0)

def nearestSquare(n):
    i = n
    while (not isSquare(i)):
        i += 1
    return i
    
def cleanMessage(s):
    s = s.translate(string.maketrans("",""), string.punctuation + string.whitespace)
    s = s.lower()
    return s

def buildSquare(s): 
    s = cleanMessage(s)
    if isSquare(len(s)):    
        size = sqrt(len(s))        
    else:
        size = sqrt(nearestSquare(len(s)))
    size = int(size)
    square = [['' for x in range(size)] for x in range(size)]
    iterator = 0
    for i in range(size):
        for j in range(size):
            if iterator < len(s):
                square[i][j] = s[iterator]
            iterator += 1
            j += 1
        i += 1
    return square

def encode(s):
    encoding = ''
    square = buildSquare(s)
    size = len(square)
    for i in range(size):
        for j in range(size):
            encoding += str(square[j][i])
        if i < size -1: encoding += ' '
    return encoding
