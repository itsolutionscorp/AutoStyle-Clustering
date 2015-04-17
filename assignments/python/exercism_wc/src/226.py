'''
Created on Sep 30, 2014
@author: bennettr
'''
def word_count(text):
    list = text.split()
    uniqueList = set(list)
    dict = {}
    for words in uniqueList:
        dict[words] = list.count(words)
    return dict
