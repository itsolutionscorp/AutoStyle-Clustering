def word_count(input):
    words = input.split()
    word_dict = {}
    for x in range(len(words)):
        word = words[x]
        if word in word_dict:
            word_dict[word] += 1
        else:
            word_dict[word] = 1
    return word_dict
