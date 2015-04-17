__author__ = 'James'
import re
def word_count(phrase):
    """
    Takes a given phrase, and returns a dictionary of word counts.
    :param phrase: Phrase to be parsed
    :return:Dictionary of word: count
    """
    words = [
        word
        for word
        in re.split(' |\n|\t', phrase)  # Split on spaces, line breaks, and tab indentations.
        if word
    ]
    return {
        key: sum(1  # Add 1 for every instance that word in words is the same as the current key.
                 for word
                 in words
                 if word == key
                 )
        for key
        in words
    }
