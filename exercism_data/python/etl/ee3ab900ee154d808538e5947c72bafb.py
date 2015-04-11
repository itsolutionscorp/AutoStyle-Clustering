def transform(legacy):
    result = {}

    for score, words in legacy.iteritems():
        for item in words:
            result.update({item.lower(): score})

    return result
