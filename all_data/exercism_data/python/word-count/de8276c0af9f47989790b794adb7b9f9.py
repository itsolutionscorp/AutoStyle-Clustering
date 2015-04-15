#wordcount.py


def word_count(phrase):     
    count={}     
    listy=phrase.split(None)     
    for i in listy:
        if count.has_key(i):             
	    count[i]+=1         
	else:             
	    count[i]=1         
    return count
