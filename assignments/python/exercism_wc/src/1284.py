def word_count(msg):
    count_dict = {}
    for word in msg.split():
        if word in count_dict:
            count_dict[word] += 1
        else:
            count_dict[word] = 1
    return count_dict
