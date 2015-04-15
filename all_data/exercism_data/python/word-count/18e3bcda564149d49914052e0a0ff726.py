def word_count(sentence):
    count = {}
    words = sentence.split()
    word_set = set(words)
    for word in word_set:
        count[word] = words.count(word)

    return count
