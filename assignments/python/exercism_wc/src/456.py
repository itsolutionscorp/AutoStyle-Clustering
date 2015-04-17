def word_count(input_list):
    my_dict = {} # create an empty dictionary
    for word in input_list.split(): # split and operate on each item
        if word not in my_dict: # for new words
            my_dict[word] = 1 # create a value of 1
        else:
            my_dict[word] += 1 # for existing words, increment the value 
    return my_dict # return the dictionary
