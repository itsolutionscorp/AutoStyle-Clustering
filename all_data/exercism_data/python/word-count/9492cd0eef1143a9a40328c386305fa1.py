import re 
import collections

def word_count(myStr):
    wcDict ={}

    myLst = re.split('\s+',myStr)
    wcDict = dict(collections.Counter( myLst ))
    return wcDict
