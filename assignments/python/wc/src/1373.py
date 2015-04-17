import re
import string
def word_count(phrase):
    words = [x.lower() for x in re.findall(r"[\w']+", phrase)]
    words = [''.join(c for c in w if c not in set(string.punctuation)) for w in words]
    count = {}
    for word in words:
        count[word] = count.get(word, 0) + 1
    return count
if __name__ == '__main__':
    print word_count('olly olly in come free')
    print word_count('testing, 1, 2 testing')
    print word_count('go Go GO')
    print word_count('car : carpet as java : javascript!!&@$%^&')
