import re
def word_count(phrase):
    assert type(phrase) == str # is it the write input?
    word_dic = {} # output dictionary
    phrase = re.split(r'[\n\t\s]+', phrase)
    for w in phrase : # split the phrase in to words
        if w != '': # make sure that '' does not count as a word
            word_dic[w] = word_dic[w] +1 if w in word_dic else 1# update count probably not the best way
    return word_dic