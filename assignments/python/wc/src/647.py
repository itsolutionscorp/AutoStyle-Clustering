def word_count(input):
    freq = {}
    list = input.split()
    unique_list = []
    for word in list:
        if word not in unique_list:
            unique_list.append(word)
    for word in unique_list:
        freq[word] = list.count(word)
    return freq
