import string
def word_count(sentence):

    word_count_dict={}
    table=string.maketrans('','')

    sentence = sentence.lower().translate(table, string.punctuation)

    for word in sentence.split():
        if word in word_count_dict:
            word_count_dict[word] += 1
        else:
            word_count_dict[word] = 1
    return word_count_dict
