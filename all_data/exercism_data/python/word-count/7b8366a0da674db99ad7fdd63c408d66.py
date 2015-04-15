# -*- coding: utf-8 -*-
def word_count(sentence):
    result=""
    word_array=sentence.split()
    setti=set(word_array)
    for word in range(len(setti)):
	sana=setti.pop()
	result+= "'%s': %d, " % (str(sana),sentence.count(str(sana)))
    result=result[:-2]
    return str(result)
