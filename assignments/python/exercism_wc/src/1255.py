import re
def word_count(phrase):
    assert type(phrase) == str # is it the write input?
    word_dic = {} # output dictionary
    phrase = re.split(r'[\n\t\s]+', phrase)
    for w in phrase : # split the phrase in to words
        word_dic[w] = word_dic[w] +1 if w in word_dic else 1 
    return word_dic
