from collections import OrderedDict

def transform(input):
    if input_contains_letter_scores(input):
        return transform_letter_scores(input)
    else:
        return transform_word_scores(input)

def input_contains_letter_scores(input):    
    # Letter scores use strings, word scores use lists of strings.
    return any(
        key for key in input
        if isinstance(input[key], basestring)
    )

def transform_letter_scores(input):
    def letter_extractor(letters):
        return letters.lower()
    return transform_scores(input, letter_extractor)

def transform_word_scores(input):
    def word_extractor(words):
        return [ word.lower() for word in words ]
    return transform_scores(input, word_extractor)

def transform_scores(input, item_extractor):
    scores = dict(
        (item, score)
        for score, element in input.items()
        for item in item_extractor(element)
    )
    return sort_scores(scores)

def sort_scores(scores):
    return OrderedDict(
        (item, scores[item])
        for item in sorted(scores.keys())
    )
