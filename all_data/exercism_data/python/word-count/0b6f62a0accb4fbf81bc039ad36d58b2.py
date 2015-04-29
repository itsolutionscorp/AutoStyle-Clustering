def word_count(sentence):
    word_count = {}
    strip_punctuation = sentence.translate(None, ",:;!&@$%^?")
    strip_punctuation = strip_punctuation.lower()
    split_words = strip_punctuation.split()
    while (len(split_words) != 0):
        if (word_count.has_key(split_words[0])):
            word_count[split_words.pop(0)] += 1
        else:
            word_count[split_words.pop(0)] = 1
    return word_count
