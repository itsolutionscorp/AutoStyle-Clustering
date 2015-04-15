from re import sub
from math import sqrt

def encode(text):
    
    if text:
        text = (sub('[^a-zA-Z0-9]','', text)).lower()
        if sqrt(len(text)) == int(sqrt(len(text))):
            columns = int(sqrt(len(text)))
            myArray = [['*' for j in range(columns)] for i in range(columns)]
        else:
            columns = int((sqrt(len(text))+1)//1)
            myArray = [['*' for j in range(columns)] for i in range(columns)]
              
        x = 0
        for i in myArray:
            for j in i:
                if x >= len(text):
                    break
                else:
                    myArray[myArray.index(i)][i.index(j)] = text[x]
                    x += 1
                
        newArray = []
        for i in range(0, len(myArray)):
            newArray.append([row[i] for row in myArray])
                
        simpleList = reduce(lambda x,y: x+[' ']+y, newArray)

        finalString = [x for x in simpleList if x is not '*']
            
        return reduce(lambda x,y: x+y, finalString)
    else:
        return ''
