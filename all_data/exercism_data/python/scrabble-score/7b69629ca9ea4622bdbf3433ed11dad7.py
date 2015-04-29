#!/usr/bin/python
from string import lowercase
def score(word):
    letters = "AEIOULNRST\nDG\nBCMP\nFHVWY\nK\nJX\nQZ".lower().split("\n")
    scores = dict(zip([1,2,3,4,5,8,10], [tuple(ls) for ls in letters]))
    scores = dict([(l, ls) for ls, lg in scores.items() for l in lg])
    score = sum(scores[c] for c in word.strip().lower())
    return score
