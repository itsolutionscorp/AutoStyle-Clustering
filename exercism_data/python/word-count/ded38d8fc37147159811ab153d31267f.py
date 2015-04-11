from collections import Counter
import string


"""
A function to parse, and return a word count from a string.

It will strip anything like tabs, carriage returns and so forth
"""

# Can't use the reserved keyword string, hence the reason I called it
# excerpt

def word_count(excerpt):
    """
    A function to return the word count of a excerpt in dictionary format.

    Example:
            >>> from wordcount import word_count
            >>> print word_count("Hello world")
            {'world': 1, 'Hello': 1}

    """
    # Validate that we are actually give something to work with
    assert excerpt, "excerpt cannot be blank"
    return Counter(excerpt.split())
