#!/usr/bin/python3


def histogram(word, case_sensitive=False):
    """
    Returns dictionary with counts of letters in word.
    """
    word = word if case_sensitive else word.lower()
    letters_lst = list(word)
    return dict((letter, letters_lst.count(letter)) for letter in word)


def detect_anagrams(word, words_lst):
    """
    Returns sublist of anagrams of word from list of possible candidates.
    """
    # calculate letter-histogram for word
    whst = histogram(word)
    # candidates list: only those, which histogram matches above
    cands = [anag_cnd for anag_cnd in words_lst if histogram(anag_cnd) == whst]
    # but don't return candidates, that are the same as word
    return [anag_cnd for anag_cnd in cands if anag_cnd.lower() != word.lower()]


if __name__ == "__main__":
    print(detect_anagrams('go', 'go Go GO'.split()))
