import string

def word_count(sentence):
    sentence = sentence.lower().translate(None,string.punctuation).split()
    return {word:sentence.count(word) for word in sentence} 
