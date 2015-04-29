def word_count(sometext):
    word_list=sometext.split()
    output_list = {}
    for word in word_list:
        if not (word in output_list):
            output_list[word] = 1
        else:
            output_list[word] += 1
    return output_list
