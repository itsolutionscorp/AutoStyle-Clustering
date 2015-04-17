from collections import Counter
def detect_anagrams(string, listString):
    strCounter = Counter(string.lower())
    retVal = []
    #loop through the list of words passed as a parameter
    for i in listString:
        listCounter = Counter(i.lower())
        #compare the count of letters in the strings
        #want to compare lower case letters
        if (strCounter == listCounter) & (string.lower() != i.lower()):
            #add the original word as sent with its capital letters
            retVal.append(i)
    return retVal
if __name__ == '__main__':
    print(detect_anagrams('banana', ['banana']))
