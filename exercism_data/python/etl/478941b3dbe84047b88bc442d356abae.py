def transform(old):
    word_scores = sum(([(word.lower(), score) for word in words]
                      for score, words in old.items()), [])
    return dict((word, score) for word, score in word_scores)
