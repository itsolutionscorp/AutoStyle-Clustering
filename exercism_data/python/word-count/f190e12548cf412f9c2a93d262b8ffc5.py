def word_count(word):
    words = word.split()
    print words
    wordkey = {}
    for key in words:
        if key in wordkey:
            wordkey[key] += 1
        else:
            wordkey[key] = 1
    return wordkey
