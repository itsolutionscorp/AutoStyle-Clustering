def word_count(sentence):

    # break out sentence down into a list
    # of individual words.
    word_list = sentence.split()
    
    word_dict = {}

    i = 0
    while i < len(word_list):
       
        # check to see if the current word is new.
        # if it is, then assign it the value of 1.
        if word_list[i] not in word_dict:
            word_dict[word_list[i]] = 1

        #if it is not, then add 1 to its value.
        else:
            word_dict[word_list[i]] += 1
        i += 1
        
    return word_dict
