def word_count(sentence):
    word_count = {}
    split_sentence = " ".join(sentence.split()) # strip extra spaces
    split_sentence = split_sentence.split(" ")
    for word in split_sentence:
        word_count[word] = split_sentence.count(word)
    return word_count
