""" count words"""
import collections
def word_count(words):
    """ word_count """
    odict = collections.OrderedDict()
    words = words.replace("\n", " ")
    for word in words.split(" "):
        if word.strip() == "":
            continue
        if word not in odict:
            odict[word] = 0
        odict[word] += 1
    return odict
