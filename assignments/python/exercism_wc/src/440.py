import string
def word_count(sentence):
    count = {}
    sentence = [word for word in sentence.lower().split(' ')]
    for word in sentence:
        word = ''.join(c for c in word if c not in set(string.punctuation))
        if not word:
            continue
        elif word in count:
            count[word] = count[word] + 1
        else:
            count[word] = 1
    return count
