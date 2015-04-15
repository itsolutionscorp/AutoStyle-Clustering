SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(4)

import itertools

def ifindall(pattern, text):
    match = -1
    while True:
        try:
            match = text.index(pattern, match + 1)
            yield match
        except ValueError:
            break

def is_sublist_of(pattern, text):
    """
    is_sublist_of(list, list) -> bool

    Return if pattern occurs in text. Uses rabin karp with an simple rolling
    hash function: hash(sequence) = sum(hash(s) for s in sequence).
    """

    if len(pattern) > len(text):
        return False

    if not pattern:
        return True

    for possible_start in ifindall(pattern[0], text):
        if text[possible_start : possible_start+len(pattern)] == pattern:
            return True

    return False


def check_lists(list_1, list_2):
    if list_1 == list_2:
        return EQUAL

    if is_sublist_of(list_1, list_2):
        return SUBLIST

    if is_sublist_of(list_2, list_1):
        return SUPERLIST

    return UNEQUAL
