import string, re

def word_count(sentence):
    '''sentence: string
       returns: dict of keys=words and values=word counts
    '''
    # strip punctuation
    sentence = sentence.translate(string.maketrans("",""), string.punctuation)

    # format spaces
    sentence = re.sub(r' +', ' ', sentence)
    sentence = sentence.strip()

    # convert all to lowercase
    sentence = sentence.lower()

    # create a list of words
    word_list = sentence.split(" ")

    # iterate through word list and generate dictionary
    result = {}
    for word in word_list:
        if word in result:
            result[word] += 1
        else:
            result[word] = 1

    return result
