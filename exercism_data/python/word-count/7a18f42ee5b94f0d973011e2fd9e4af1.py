'''
        Four Solutions
'''        




from collections import *

def word_count(phrase):
    nwords = {}
    words = phrase.split()
    for f in words:
        if not nwords.get(f):
            nwords[f] = 1
        else:     
           nwords[f] += 1
    return nwords

'''
def word_count(phrase):
    nwords = {}
    words = phrase.split()
    for f in words:
        if not nwords.get(f):
           nwords[f] = words.count(f)
    return nwords
'''

'''    
def word_count(phrase):
    nwords = defaultdict(lambda: 0)
    for f in phrase.split():
        nwords[f] += 1
    return nwords
'''

'''
def word_count(phrase):
    return Counter(phrase.split())
'''

