"""Sublist"""

from itertools import islice, izip


SUBLIST, SUPERLIST, EQUAL, UNEQUAL = (
    "SUBLIST", "SUPERLIST", "EQUAL", "UNEQUAL"
)


def check_lists(lst1, lst2):
    """Return the relation between two lists."""
    if find(lst1, lst2) >= 0:
        if find(lst2, lst1) >= 0:
            return EQUAL
        else:
            return SUPERLIST
    elif find(lst2, lst1) >= 0:
        return SUBLIST
    else:
        return UNEQUAL


def find(sup, sub):
    """Return the lowest index in the first list where the second one is found.

    Return -1 if the second list is not found.
    """
    sup_len, sub_len = len(sup), len(sub)
    if sup_len < sub_len:
        return -1

    if not sub:
        return 0
    sub_head, sub_tail = sub[0], sub[1:]

    pos = 0
    last_pos = sup_len - sub_len
    while pos <= last_pos:
        try:
            pos = sup.index(sub_head, pos, last_pos + 1)
        except ValueError:
            return -1
        # assert sup[pos] == sub_head
        v1v2 = izip(islice(sup, pos + 1, sup_len), sub_tail)
        if all(v1 == v2 for v1, v2 in v1v2):
            return pos
        pos += 1
    else:
        return -1
