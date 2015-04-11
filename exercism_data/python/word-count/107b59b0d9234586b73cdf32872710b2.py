from re import sub

def word_count(s):

    '''Returns dictionary of words in sentence s and their respective counts.
    Words are alphanumeric and lowercase and separated by whitespace.''' 

    wordlist = sub(r'[^\w\s]+', '', s).lower().split()

    return dict((word, wordlist.count(word)) for word in wordlist)
    
