def word_count(string):
    string = string.split() 
    return dict((word,string.count(word)) for word in string)
