# -*- coding: utf-8 -*-

#word count 
#return a dictionary with the count of the words in a sentance

def word_count(text):
    
    #create a dictionary
    words = {}

    #return a list of all the words
    all_words = text.split()
    print all_words
    
    #iterate through the words and add/increment the dictionary
    for w in all_words:
        if w in words:
            words[w] += 1
        else:
            words[w] = 1
            
    print words
    return words
