scores = dict()
scores.update({k: 1 for k in 'aeioulnrst'})
scores.update({k: 2 for k in 'dg'})
scores.update({k: 3 for k in 'bcmp'})
scores.update({k: 4 for k in 'fhvwy'})
scores.update({k: 5 for k in 'k'})
scores.update({k: 8 for k in 'jx'})
scores.update({k: 10 for k in 'qz'})


def score(word):
    return sum([scores[letter.lower()]
                for letter in word
                if letter.isalpha()])
