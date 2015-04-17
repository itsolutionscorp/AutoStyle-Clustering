def word_count(text):
    words=text.strip().split()
    dict={}
    for word in words:
        if word not in dict:
            dict[word]=1
        else:
            dict[word]=dict[word]+1
    return dict
