def word_count(words):
    words = words.split()
    wordCounter = {}
    for i in words:
        if i not in wordCounter:
            wordCounter[i] = 1
        else:
            wordCounter[i] += 1
    return wordCounter
