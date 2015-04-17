import re
def word_count(input):
    stripStr = re.sub(r'[!&@$%^:,]', '', input)
    stripStr = stripStr.lower()
    stripStr = stripStr.split()
    wordsDict = {}
    for word in stripStr:
        if not word in wordsDict.keys():
            wordsDict[word] = 1
        else:
            wordsDict[word] = wordsDict[word] + 1
    return wordsDict
