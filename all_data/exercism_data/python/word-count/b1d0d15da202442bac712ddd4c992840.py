def word_count(phrase):
    
    l = phrase.split() # convert string into list
    ul = [] # instantiate empty list
    ans = {} # instantiate empty dict
    #create a list of unique words
    for i in l:
        if i not in ul:
            ul.append(i)        
    
    #for i in ul:
        #ans[i]= l.count(i)
    #print ans


    for i in ul:
        print i + ": " + str(l.count(i))
        
                        
