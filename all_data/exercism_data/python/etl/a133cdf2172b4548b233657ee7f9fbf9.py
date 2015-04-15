def transform(letters_by_score):
    score_by_letter = {}
    for score, letters in letters_by_score.items():
        for letter in letters:
            score_by_letter[letter.lower()] = score
    return score_by_letter
