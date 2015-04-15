import string


def word_count(sentence):
    no_punctuation = sentence.translate(string.maketrans("",""), string.punctuation)

    normalized_case = no_punctuation.lower()

    words = normalized_case.split()

    unique_words = {}

    for word in words:
    	if word not in unique_words:
    		unique_words[word] = 1
    	elif word in unique_words:
    		unique_words[word] += 1
    
    return unique_words
