def word_count(sentence):
    words = sentence.split()
    count = {} 

    for word in words:
        count[word] = 0

    for word in words:
        count[word] += 1

    return count
