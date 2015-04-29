def word_count(sentence):
    count = {}
    for word in sentence.split():
        if word:
            count[word] = count[word] + 1 if word in count else 1
    return count
