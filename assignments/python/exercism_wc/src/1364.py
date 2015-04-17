from collections import Counter
import string
"""
A function to parse, and return a word count from a string.
It will strip anything like tabs, carriage returns and so forth
"""
def word_count(excerpt):
    """
    A function to return the word count of a excerpt in dictionary format.
    Example:
            >>> from wordcount import word_count
            >>> print word_count("Hello world")
            {'world': 1, 'Hello': 1}
    """
    assert excerpt, "excerpt cannot be blank"
    return Counter(excerpt.split())
