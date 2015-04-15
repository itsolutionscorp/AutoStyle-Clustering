def transform(old_scoring):
    scoring = {}
    for score, values in old_scoring.items():
        for value in values:
            scoring[value.lower()] = score
    return scoring
