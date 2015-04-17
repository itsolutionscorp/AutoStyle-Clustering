"""
Created on Thu Dec 04 17:25:29 2014
@author: Admin
"""
def word_count(texte):
    words = texte.split()
    wc = dict()
    for word in words:
        wc[word] = wc.get(word, 0) + 1
    return wc
