"""
anagram

"""


def _is_anagram(w0, w1):
    """
    Test if w0 and w1 are anagrams

    :param w0:
    :param w1:
    :return:
    """
    w0 = ''.join(sorted(ch.lower() for ch in w0))
    w1 = ''.join(sorted(ch.lower() for ch in w1))
    return w0 == w1

def detect_anagrams(word, possible_anagrams):
    """
    Get a list of anagrams of word within list of
    possible_anagrams

    :param word:
    :param possible_anagrams:
    :return:
    """
    return [w for w in possible_anagrams if _is_anagram(word, w) and word.lower() != w.lower()]
