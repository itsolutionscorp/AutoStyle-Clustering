def word_count(prompt):
    wordArray = prompt.split()
    wordCount = {}
    for word in wordArray:
        currentValue = 0
        try:
            currentValue = wordCount[word]
        except:
            pass
        wordCount.update({word: currentValue + 1})
    return wordCount
