def word_count(words):
    """returns a dictionary of words and their count"""

    
    #creates a dictionary of words
    dict1={}
    for word in words.split():
        if dict1.has_key(word):
            dict1[word]+=1
        else:
            dict1[word]=1
            
    return dict1
