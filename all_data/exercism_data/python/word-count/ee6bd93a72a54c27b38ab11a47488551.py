def word_count(prompt):
    # This will give us each word
    wordArray = prompt.split()
    # Establish a dictionary to retain word counts
    wordCount = {}
    for word in wordArray:
        # We know we will get an exception when the key
        # doesn't exist
        try:
            currentValue = wordCount[word]
        except KeyError:
            currentValue = 0
        # Need to use update method, or get an exception
        wordCount.update({word: currentValue + 1})

    return wordCount
