import string

def word_count(phrase):
    '''count occurences of words in a phrase, ignoring case and punctuation'''
    d = {}
    for word in phrase.lower().split():
        word = word.strip(string.punctuation)
        if not len(word): pass
        elif word in d: d[word] +=1
        else: d[word] = 1
    return(d)
