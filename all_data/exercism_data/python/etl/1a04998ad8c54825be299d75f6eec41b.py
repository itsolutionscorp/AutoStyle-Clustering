from itertools import chain


def transform(data):
    return dict(chain.from_iterable([
        [(word.lower(), score) for word in words]
        for (score, words) in data.items()
    ]))
