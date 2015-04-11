import string

def word_count(sentence):
    word_list = [x.strip(string.punctuation).lower()
                 for x in sentence.split()
                 if x.strip(string.punctuation).lower().isalpha()
                 or x.strip(string.punctuation).lower().isdigit()]
    word_dict = {}
    for word in word_list:
        word_dict[word] = 0
    for word in word_list:
        word_dict[word] += 1
    return word_dict
