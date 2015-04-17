from collections import Counter
def word_count(sentence):
    sentence = sentence.replace("\n", " ")
    sentence = sentence.replace("   ", "") 
    sentence = sentence.split(' ')
    return Counter(sentence)
