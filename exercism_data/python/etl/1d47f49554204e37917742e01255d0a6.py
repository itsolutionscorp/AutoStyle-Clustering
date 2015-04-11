def transform(old):
    result = {}
    for score, word_list in old.items():
        result.update({word.lower(): score for word in word_list})

    return result
