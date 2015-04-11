from typing import List


def slices(iterable: str, r: int) -> List[List[int]]:
    size = len(iterable) - r + 1
    if r == 0 or size < 1:
        raise ValueError

    return [[int(y) for y in iterable[x:x + r]] for x in range(size)]
