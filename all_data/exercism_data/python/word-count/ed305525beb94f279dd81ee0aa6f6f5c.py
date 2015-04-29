def word_count(msg):
    # convert string to list
    msg = msg.split()
    # initialize empty dictionary
    dic = {}
    
    # loop until the list is empty
    while len(msg) > 0:
        # get the count of the first word in the list
        # and add it to the dictionary
        word = msg[0]
        count = msg.count(word)
        dic[word] = count
        # remove count instances from the list
        for i in range(count):
            msg.remove(word)
    return dic
