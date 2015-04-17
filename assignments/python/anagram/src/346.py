from collections import Counter
def detect_anagrams(test_str, wordList):
    returnList = []
    checkList = Counter(test_str.upper())
    for word in wordList:
        if len(test_str) == len(word):
            if test_str.upper() != word.upper():
                if checkList == Counter(word.upper()):
                    returnList.append(word)
    return returnList
