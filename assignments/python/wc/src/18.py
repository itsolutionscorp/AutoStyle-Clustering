import re
def word_count(phrase):
    words = re.sub(r'[^a-z0-9\[\]]',' ', phrase.lower()).split() # is this line too confusing?
    word_count = {}
    for word in words:
        word_count[word] = word_count[word] + 1 if word in word_count else 1
    return word_count
