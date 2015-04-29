def word_count(input_string):

    wordcount = {}
    count = 0

    # split the input into words and remove duplicates
    orig_word_list = input_string.split()    
    final_word_list = list(set(orig_word_list))

    for item in final_word_list:
        for word in orig_word_list:
            if item == word:
                count += 1
        wordcount[item] = count
        count = 0

    return wordcount
