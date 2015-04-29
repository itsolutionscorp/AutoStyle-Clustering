import string

def word_count(str):    
    words = (cleanPunctuation(str).lower()).split()
    l = {}
    for i in words:
        if not l.has_key(i):
            l[i] = 1
        else:
            l[i] = l[i] + 1
    
    return l

def cleanPunctuation(str):
    """Takes out string.punctuation characters off a string."""
    x = string.maketrans("","") #Make blank dictionary
    return str.translate(x, string.punctuation)

if __name__ == '__main__':
    word_count('one fish two fish red fish blue fish')
