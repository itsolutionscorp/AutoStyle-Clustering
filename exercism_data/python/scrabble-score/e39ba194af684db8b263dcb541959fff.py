from itertools import cycle
from functools import reduce


scores = dict(zip('AEIOULNRST', cycle([1])))
scores.update(zip('DG', cycle([2])))
scores.update(zip('BCMP', cycle([3])))
scores.update(zip('FHVWY', cycle([4])))
scores.update(zip('KJXQZ', [5, 8, 8, 10, 10]))


def score(line):
    return reduce(lambda total, c: total + scores[c], line.strip().upper(), 0)
