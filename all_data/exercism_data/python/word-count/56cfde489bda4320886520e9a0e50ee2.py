import string

def word_count(input):
    wordcount = {}
    words = [word.strip(string.punctuation).lower() for word in input.split()]
    
    for word in words:
        if word == '':
            continue
        elif word in wordcount.keys():
            wordcount[word] = wordcount[word] +1
        else:
            wordcount[word] = 1
            
    return wordcount
