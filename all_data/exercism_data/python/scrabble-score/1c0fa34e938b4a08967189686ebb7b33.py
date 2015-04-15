lettervals = {'aeioulnrst': 1, 'dg': 2, 'bcmp': 3, 'fhvwy': 4, 'k': 5, 'jx': 8, 'qz': 10}
vallookup = {}
for k, v in lettervals.iteritems():
    for letter in k:
        vallookup[letter] = v

def score(word):
    total = 0
    for letter in word.lower():
        total += vallookup.get(letter, 0)
    return total
