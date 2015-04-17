def word_count(prompt):
    wordArray = prompt.split()
    wordCount = {}
    for word in wordArray:
        try:
            currentValue = wordCount[word]
        except KeyError:
            currentValue = 0
        wordCount.update({word: currentValue + 1})
    return wordCount
