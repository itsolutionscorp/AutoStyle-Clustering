def word_count(phrase):
    mylist = phrase.split()
    myset = set(mylist)
    mydict = {i: mylist.count(i) for i in myset}
    return mydict
