import string

def word_count(words):
    words = "".join(w for w in words if w in string.ascii_letters+string.digits+string.whitespace)
    word_list = [w.lower() for w in words.split()]
    counter = {}
    for word in set(word_list):
        counter[word] = word_list.count(word)
    print counter
    return counter
