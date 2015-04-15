from functools import reduce
from typing import List
import operator


def largest_product(iterable: str, length: int) -> int:
    largest = 0
    for series in slices(iterable, length):
        p = reduce(operator.mul, series, 1)
        if p > largest:
            largest = p
    return largest


def slices(iterable: str, length: int) -> List[List[int]]:
    size = len(iterable) - length + 1
    if size < 1:
        raise ValueError

    return [[int(y) for y in iterable[x:x + length]] for x in range(size)]
