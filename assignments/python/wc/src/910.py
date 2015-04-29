def word_count(words):
    """returns a dictionary of words and their count"""
    word_list=words.split()
    dict1={}
    for word in word_list:
        if dict1.has_key(word):
            print dict1
            temp=dict1[word]
            temp+=1
            dict1[word]=temp
        else:
            dict1[word]=1
    return dict1
