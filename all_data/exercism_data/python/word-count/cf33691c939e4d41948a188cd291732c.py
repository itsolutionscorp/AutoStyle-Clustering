def word_count(input):
    input = input.lower()
    validChars = 'abcdefghijklmnopqrstuvwxyz1234567890 '
    for char in input:
        if char not in validChars:
            input = input.replace(char, '')

    words = input.split()
    wordDict = {}

    for word in words:
        if  word in wordDict:
            wordDict[word] += 1
        else:
            wordDict[word] = 1

    print(wordDict)
    return wordDict
