import string, re
def word_count(sentence):
    '''sentence: string
       returns: dict of keys=words and values=word counts
    '''
    sentence = sentence.translate(string.maketrans("",""), string.punctuation)
    sentence = re.sub(r' +', ' ', sentence)
    sentence = sentence.strip()
    sentence = sentence.lower()
    word_list = sentence.split(" ")
    result = {}
    for word in word_list:
        if word in result:
            result[word] += 1
        else:
            result[word] = 1
    return result
