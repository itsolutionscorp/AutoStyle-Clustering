import re
def word_count(phrase,wordcount):
    wordcount = {}
    for word in re.split(' |\n|\t', phrase):
	    if word not in wordcount:
		    wordcount[word] = 1
            else:
                wordcount[word] +=1
