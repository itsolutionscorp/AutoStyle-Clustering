def word_count(phrase):
    mylist = phrase.split()
    mydict = {i: mylist.count(i) for i in mylist}
    return mydict
