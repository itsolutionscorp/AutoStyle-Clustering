def word_count(s):
    words = s.split() # make list of words
    count = {word:0 for word in words} # dictionary to store count for each word, count set to 0 for each at first
    for word in words:
        count[word] += 1
    return count
