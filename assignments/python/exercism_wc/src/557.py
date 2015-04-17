import string
def word_count(phrase):
    count = dict()
    phrase = phrase.translate(None, string.punctuation)
    phrase = phrase.lower()
    for word in phrase.split():
        count[word] = count.get(word,0) + 1
    return count
