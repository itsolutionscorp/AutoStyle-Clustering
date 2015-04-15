SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(4)

class RollingHash:
    def __init__(self, start):
        self._hash = sum(hash(s) for s in start)

    def feed(self, old, new):
        self._hash += hash(new) - hash(old)

    def __eq__(self, other):
        return self._hash == other._hash


def is_sublist_of(pattern, text):
    """
    is_sublist_of(list, list) -> bool

    Return if pattern occurs in text. Uses rabin karp with an simple rolling
    hash function: hash(sequence) = sum(hash(s) for s in sequence).
    """

    if len(pattern) > len(text):
        return False

    patternH = RollingHash(pattern)
    textH = RollingHash(text[:len(pattern)])

    if patternH == textH:
        if pattern == text[:len(pattern)]:
            return True

    for i in range(1, len(text) - len(pattern) + 1):
        #check pettern at text[i : i + len(pattern)]
        textH.feed(text[i - 1], text[i + len(pattern) - 1])
        if patternH == textH:
            if pattern == text[i : i+len(pattern)]:
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
