#-------------------------------------------------------------------------------
# Name:        wordcount
# Purpose:     exercism.io module 3
#
# Author:      AFMac
#
# Created:     27/09/2014
#-------------------------------------------------------------------------------
import collections

def word_count(s):
    sList = s.split()
    sCnt = []
    for word in sList:
        sCnt.append(sList.count(word))
    return dict(collections.OrderedDict(zip(sList,sCnt)))

