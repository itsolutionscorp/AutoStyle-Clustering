# -*- coding: utf-8 -*-

def word_count(phrase):

    dic_words={}
    wordlist=phrase.split()
    for word in wordlist:
        dic_words[word]=wordlist.count(word)   
   
    return dic_words
