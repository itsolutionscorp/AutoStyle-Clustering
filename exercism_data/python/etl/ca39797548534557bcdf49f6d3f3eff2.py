def transform(scores):
    new_scores = {}
    for key, values in scores.iteritems():
        new_scores.update({value.lower(): key for value in values})
    return new_scores
