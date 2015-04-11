def word_count(sentence):
    dict = {}
    for i in sentence.split():
        if i not in dict:
            dict[i] = 0
        dict[i]+=1
    return dict
        
    
