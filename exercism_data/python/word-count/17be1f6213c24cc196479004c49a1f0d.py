def word_count(input):
    """ Counts the words in an input string
    """
    words = input.split()
    unique_words = set(words)
    val = dict()
    for word in unique_words:
        val[word] = occurances_in_list(word, words)
    return val

def occurances_in_list(word, in_list):
    """ counts the occurances of a word in a word list
    """
    val = 0
    for test in in_list:
        if test == word:
            val += 1
    return val
