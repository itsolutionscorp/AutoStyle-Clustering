def word_count(phrase):
    l = phrase.split() # convert string into list
    ul = [] # instantiate empty list
    ans = {} # instantiate empty dict
    for i in l:
        if i not in ul:
            ul.append(i)        
    for i in ul:
        print i + ": " + str(l.count(i))
