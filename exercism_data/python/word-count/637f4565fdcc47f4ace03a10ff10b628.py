from string import letters, digits

def word_count(sentence):
    sentence       = sentence.lower()

    #determine which chars do not make up a word
    #(excl. space, need that to split)
    non_word_chars = sentence.translate(None, digits + letters +' ')

    #remove them and split we go
    sentence       = sentence.translate(None, non_word_chars).split(' ')
    words          = {}

    for word in sentence:
        word = word.strip()
        if word in words:
            words[word] += 1
        elif word != '':
            words[word] = 1
    return words
