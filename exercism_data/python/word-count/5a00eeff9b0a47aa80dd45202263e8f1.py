import string
def word_count(sen):
    wcount={}
    chars = set(string.punctuation)
    sen = ''.join(ch for ch in sen if ch not in chars)
    sen = sen.split()
    for word in sen:
        if word.lower() in wcount:
            wcount[word.lower()] += 1
        else:
            wcount[word.lower()] = 1
    return wcount
