def word_count(s):
    words = s.split() # make list of words
    count = {word:words.count(word) for word in words}
    return count
