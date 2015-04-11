from collections import Counter

scores = dict.fromkeys(['a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'], 1)
scores.update(dict.fromkeys(['d', 'g'], 2))
scores.update(dict.fromkeys(['b', 'c', 'm', 'p'], 3))
scores.update(dict.fromkeys(['f', 'h', 'v', 'w', 'y'], 4))
scores.update(dict.fromkeys(['k'], 5))
scores.update(dict.fromkeys(['j', 'x'], 8))
scores.update(dict.fromkeys(['q', 'z'], 10))

def score(word):
    cnt = Counter(word.strip().lower())
    return(sum([scores[letter]*cnt[letter] for letter in cnt]) or 0)
