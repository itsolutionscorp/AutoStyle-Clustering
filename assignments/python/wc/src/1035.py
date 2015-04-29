import string
def word_count(sentence):
    key = string.maketrans("","")
    no_punctuation = sentence.translate(key, string.punctuation)
    normalized_case = no_punctuation.lower()
    words = normalized_case.split()
    unique_words = {}
    for word in words:
        get_word_amount= unique_words.get(word, 0) 
    	unique_words[word] = get_word_amount + 1
    return unique_words
