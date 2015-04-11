def word_count(what):

    mydict = {}  #create hashtbl/"dictionary"/"associative array"            79

    for i in what.split():
        mydict[i] = mydict.get(i, 0) + 1

    return mydict
