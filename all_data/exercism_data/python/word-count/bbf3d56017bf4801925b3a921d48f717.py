def word_count(phrase):
    
    assert type(phrase) == str # is it the write input?
    
    word_dic = {} # output dictionary
    
    for r in ['\n','\t']: # Remove escape characters
        phrase=phrase.replace(r,' ')
    
    for w in phrase.split(' '): # split the phrase in to words
        if w != '': # make sure that '' does not count as a word
            try:
                word_dic[w] = word_dic[w] +1# update count probably not the best way
            except:
                word_dic[w] = 1 # if not in dictionary include it
                
    return word_dic
