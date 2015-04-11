def transform(d):
    return {letter.lower(): letter_score
            for letter_score, letter_list in d.items()
            for letter in letter_list}
