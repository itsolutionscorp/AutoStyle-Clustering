import string
def word_count(wordString):
    exclude = set(string.punctuation)
    wordString = ''.join(ch for ch in wordString if ch not in exclude)
    stringArray = wordString.split()
    uniques = set(stringArray)
    stringArray = [x.lower() for x in stringArray]
    uniques = [x.lower() for x in uniques]
    d = {}
    for word in uniques:
        count = 0
        for oldWord in stringArray:
            if (oldWord == word):
                count += 1
        d[word] = count
    return d
