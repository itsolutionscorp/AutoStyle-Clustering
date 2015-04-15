data = [(['a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'], 1),
(['d', 'g'], 2),
(['b', 'c', 'm', 'p'], 3),
(['f', 'h', 'v', 'w', 'y'], 4),
(['k'], 5),
(['j', 'x'], 8),
(['q', 'z'], 10),]

scores = {}

for letters, points in data:
    for letter in letters:
        scores[letter] = points

def score(word):
    word = word.strip().lower()
    return sum(scores[ch] for ch in word)
