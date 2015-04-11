from typing import Iterable


def sum_of_multiples(limit: int, multiples: Iterable[int]=(3, 5)) -> int:
    total = 0
    for number in range(1, limit):
        for multiple in (m for m in multiples if m):
            if not number % multiple:
                total += number
                break
    return total
