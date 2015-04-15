def sieve(what):
    #creating the list of ints
    mylist = [i for i in range(2,what)]
    #starting from j and eliminating all multiples
    for j in range(2,what):
        for i in mylist:
            if i>j and i%j==0:
                mylist.remove(i)
    return mylist
