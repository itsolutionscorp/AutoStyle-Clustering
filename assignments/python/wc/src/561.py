import collections
def word_count(s):
    sList = s.split()
    sCnt = []
    for word in sList:
        sCnt.append(sList.count(word))
    return dict(collections.OrderedDict(zip(sList,sCnt)))
