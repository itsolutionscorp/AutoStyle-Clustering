def transform(old):
    result = {}
    [[result.update({word.lower(): score}) for word in words]
     for score, words in old.items()]
    return result
