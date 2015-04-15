SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(4)

def is_sublist_of(pattern, text):
    """
    is_sublist_of(list, list) -> bool

    Return if pattern occurs in text. Uses knuth morris pratt matching wich is
    linear in the length of the text. See [1] for a slightly different version.

    [1] http://code.activestate.com/recipes/117214/
    """

    # build table of shift amounts
    shifts = [1] * (len(pattern) + 1)
    shift = 1
    for pos, c in enumerate(pattern):
        while shift <= pos and c != pattern[pos - shift]:
            shift += shifts[pos - shift]
        shifts[pos + 1] = shift

    # do the actual search
    startPos = 0
    matchLen = 0
    for c in text:
        while (matchLen == len(pattern)
               or matchLen >= 0 and pattern[matchLen] != c):
            startPos += shifts[matchLen]
            matchLen -= shifts[matchLen]
        matchLen += 1
        if matchLen == len(pattern):
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
