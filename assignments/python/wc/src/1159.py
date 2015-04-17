def word_count(string):
    newstring = string.replace('\n', ' ')
    newstring = sorted(newstring.split(' '))
    outDict = {}
    firstArray = []
    for aString in newstring:
        if aString != '':
            firstArray.append(aString)
    for bString in firstArray:
        if bString in outDict:
            outDict[bString] += 1
        else:
            outDict[bString] = 1
    return outDict
