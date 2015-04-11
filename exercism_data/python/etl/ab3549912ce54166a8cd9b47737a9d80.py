def transform(scores):
    result = dict([])
    for score, words in scores.items():
        for word in words:
            result[word.lower()] = score
    return result
