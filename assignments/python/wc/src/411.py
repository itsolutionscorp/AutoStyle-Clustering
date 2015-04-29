def word_count(sentence):
    s=sentence.split()              #splitting the string on whitespaces
    b=set(s)                        #cast to a set for uniquification(is this a word?)
    d={x:s.count(x) for x in b}     #iterate over the set, counting occurrences in the list
    return d
