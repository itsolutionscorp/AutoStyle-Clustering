def word_count(prompt):
    # This will give us each word
    wordArray = prompt.split()
    # Establish a dictionary to retain word counts
    wordCount = {}
    for word in wordArray:
        # This is all to get the current value (if it exists)
        currentValue = 0
        # Just avoid the exception, we don't care
        try:
            currentValue = wordCount[word]
        except:
            pass
        # Need to use update method, or get an exception
        wordCount.update({word: currentValue + 1})

    return wordCount
