def word_count(words):
    """Transform words from a string of words to an array of words.  Then map
       those words into a dict."""
    word_map = {}
    for w in words.split():
        if w in word_map:
            word_map[w] += 1
        else:
            word_map[w] = 1
    return word_map
