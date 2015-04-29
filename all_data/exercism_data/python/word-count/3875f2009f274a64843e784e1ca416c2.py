import string

def word_count(sentence):
    sentence = sentence.translate(string.maketrans('', ''), string.punctuation)  #remove punctuation
    sentence = sentence.lower().split()  # Converto to lowercase and split by words
    unique_words = list(set(sentence))
    return {word: sentence.count(word) for word in unique_words}
