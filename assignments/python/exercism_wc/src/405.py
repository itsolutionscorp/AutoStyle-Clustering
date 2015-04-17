"""exercism.io - Python 'word-count' exercise solution."""
import re
def word_count(str_):
    """
    Creates a word count dictionary and returns it.
    {word: wordOccurences, word2: word2Occurences}
    eg. input: 'foo bar foo'
        output: {foo: 2, bar: 1}
    """
    word_count_dictionary = {}
    str_ = str_.lower()
    for word in re.split(r'\W+', str_):
        if not word:
            continue
        if word in word_count_dictionary:
            word_count_dictionary[word] += 1
        else:
            word_count_dictionary[word] = 1
    return word_count_dictionary
