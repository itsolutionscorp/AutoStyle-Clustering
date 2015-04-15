# -*- coding: utf-8 -*-
def word_count(sentence):
    result=""
    word_array=sentence.split()
    setti=set(word_array)
    for word in range(len(setti)):
	sana=setti.pop()
	count=0
	for word2 in range(len(word_array)):
	    if str(sana)==word_array[word2]:
	        count=count+1
	result+= "'%s': %s, " % (str(sana),count)
    result=result[:-2]
    return str(result)
