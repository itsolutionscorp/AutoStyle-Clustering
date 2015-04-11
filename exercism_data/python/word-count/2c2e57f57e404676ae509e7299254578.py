def word_count(words):
    # split words into dict
    words = words.split()

    # dict for counting words
    wordCounter = {}

    #iterate over dict
    for i in words:
        if i not in wordCounter:
            wordCounter[i] = 1
        else:
            wordCounter[i] += 1

    return wordCounter
