def word_count(phrase):
    counter = {}
    word_list = phrase.split()
    for word in word_list:
        if word not in counter:
            counter[word] = 1
        else:
            counter[word] += 1
    return counter
