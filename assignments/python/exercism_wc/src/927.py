def word_count(sentence):
    temp1 = sentence.replace('  ','')
    temp2 = temp1.replace('\n',' ')
    words = temp2.split(' ')
    word_dict = {} # declare dictionary
    for i in words:
        count = words.count(i) 
        word_dict[i] = count
    return word_dict
