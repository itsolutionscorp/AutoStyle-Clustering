from collections import Counter
def word_count(what):
    what = what.replace("\n", " ")
    what = what.replace("      ", "") 
    what = what.split(' ')
    return Counter(what)
