'''
Created on Oct 2, 2014
@author: dulshani
'''
def word_count(sentence):
    word_list = {}
    for word in sentence.split():
        if word in word_list:
            word_list[word] += 1
        else:
            word_list[word] = 1
    return word_list
