from string import letters, digits
def word_count(sentence):
    sentence       = sentence.lower()
    non_word_chars = sentence.translate(None, digits + letters +' ')
    sentence       = sentence.translate(None, non_word_chars).split(' ')
    words          = {}
    for word in sentence:
        word = word.strip()
        if word in words:
            words[word] += 1
        elif word != '':
            words[word] = 1
    return words
