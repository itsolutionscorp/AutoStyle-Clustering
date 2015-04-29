#---------------------------------
# Name:        McFace3000
# Purpose:      Sulci and Gyri!

#---------------------------------
from string import maketrans

test = '73167176531330624919225119674426574742355349194934'
#Below:  Walks through the variable Test at the pace of variable Size until it
#Reaches the end of variable Test
def slices(test, size):
    if (len(test) < size):
        raise ValueError('Size is bigger than length of variable')
    start = 0
    accident = "'"
    even = 2
    new = []
    test = map(int, str(test))
    while test[start:start+size] != test[-size +1:]:
        new.append([test[start:start+size]])
        start += 1
    experiment = new
    newest = []
    transfer = []
    count = 1
    for x in experiment:
        for y in x:
            for z in y:
                transfer.append(int(z))
                if (count % size == 0) and (count > 0):
                    newest.extend([transfer])
                    transfer = []
                    count += 1
                else:
                    count +=1

    return newest



def largest_product(test, size):
    test = slices(test, size)
    total = 1
    count = 0
    totals = []
    for x in test:
        for y in x:
            total *= int(y)
        totals.append(total)
        total = 1
    if len(totals) == 0:
        return 1
    else:
        return max(totals)
