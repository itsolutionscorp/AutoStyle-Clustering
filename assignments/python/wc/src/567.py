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
        if word  # Ignores empty strings.
    ]
    return {key: words.count(key) for key in words}
