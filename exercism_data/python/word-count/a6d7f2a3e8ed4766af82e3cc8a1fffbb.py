def word_count(in_string):
    in_string = str(in_string)
    words = in_string.split()
    count_words = dict()
    for word in words:
        if word in count_words:
            count_words[word] += 1
        else:
            count_words[word] = 1

    return count_words 
