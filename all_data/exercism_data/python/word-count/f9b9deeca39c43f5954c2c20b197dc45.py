import re

def word_count(sentence):
    ''' return a with each word in sentence being a key
    with a value equal to the number of times word
    appears in sentence'''
    
    sentence = sentence.lower()
    word_dic = {}

    words = re.findall(r'\w+', sentence)
    
    for word in words:
        
        if word in word_dic:
            word_dic[word] += 1
            
        else:
            word_dic[word] = 1
            
    return word_dic
