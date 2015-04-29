# -*- coding: utf-8 -*-
def word_count(sentence):
    result=dict()
    word_array=sentence.split()
    setti=set(word_array)
    for word in range(len(setti)):
	sana=setti.pop()
	count=0
	for word2 in range(len(word_array)):
	    if str(sana)==word_array[word2]:
	        count=count+1
	result[str(sana)]=count
    return result
