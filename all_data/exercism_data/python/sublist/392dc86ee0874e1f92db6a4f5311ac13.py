from collections import Counter

UNEQUAL = 0
SUBLIST = 1
SUPERLIST = 2
EQUAL = 3

# This works for set comparison right now, essentially.
# The issue is when you have one set that has doubles,
# and the other course only has singletons. They aren't
# going to show up sequentially, so it doesn't make sense
# to compare substrings.

# I might go back to my original idea.


class ListComparer(Counter):
    """Extends the Counter class from collections
    to allow comparisons between counters."""
    def __le__(self, other):
        return all((self[key] <= other[key] for key in self.keys()))

    def __ge__(self, other):
        return all((other[key] <= self[key] for key in other.keys()))


def check_lists(first_list, second_list):
    first_comparer = ListComparer(first_list)
    second_comparer = ListComparer(second_list)
    return ((first_comparer <= second_comparer)
            + 2 * (first_comparer >= second_comparer))
