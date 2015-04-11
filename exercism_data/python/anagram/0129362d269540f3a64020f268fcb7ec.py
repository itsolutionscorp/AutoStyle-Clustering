from itertools import permutations


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

    if search == word:
        return False

    return tuple(sorted(word)) in sorted(permutations(search))
