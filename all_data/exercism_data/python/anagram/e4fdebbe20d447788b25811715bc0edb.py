def detect_anagrams(search, words):
    """
    Detect all anagrams in a given list.

    :param str search:
    :param [str] words:
    :return: [str]
    """
    return [word for word in words if is_anagram(search, word)]


def is_anagram(search, word):
    """
    Check, whether :word is an anagram of :search.

    :param str search:
    :param str word:
    :return: boolean
    """
    search = search.lower()
    word = word.lower()

    return sort(search) == sort(word) and not search == word


def sort(x):
    """
    Sort a string in the alphabetical order.

    :param str x:
    :return: str
    """
    return ''.join(sorted(x))
