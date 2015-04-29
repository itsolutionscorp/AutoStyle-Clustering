def score(word):
    scrabble_scores = [(1, 'aeioulnrst'), (2, 'dg'), (3, 'bcmp'), (4, 'fhvwy'), (5, 'k'), (8, 'jx'), (10, 'qz')]
    letter_scores = {letter:score for score, letters in scrabble_scores for letter in letters}

    return sum([letter_scores.get(i, 0) for i in word.lower()])
