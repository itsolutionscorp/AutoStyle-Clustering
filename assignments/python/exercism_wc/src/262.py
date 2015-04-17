def word_count(stringToCheck):
    stringToCheck = ' '.join(stringToCheck.split())
    wordList = stringToCheck.split()
    seen = {}
    for x in wordList:
        if x in seen:
            seen[x] += 1
        else:
            seen[x] = 1
    return seen
