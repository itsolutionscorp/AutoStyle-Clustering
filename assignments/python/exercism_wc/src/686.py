def word_count(sentence): 
    "counts appearances of unique words in a phrase"
    word_list=sentence.split()
    d={}
    for word in word_list:
        count=word_list.count(word)
        d[word]=count
    return d
