def word_count(phrase):
    from collections import OrderedDict
    phrase = phrase.split()
    uniquewords = list(OrderedDict.fromkeys(phrase))
    return dict((w,phrase.count(w)) for w in uniquewords)