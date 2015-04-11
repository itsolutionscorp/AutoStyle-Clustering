def word_count(phrase) :
    word_dict = {}
    
    for word in phrase.replace("\n"," ").split(" ") :
        if len(word) != 0 :
            word_dict[word] = word_dict.get(word,0) + 1
        
    return word_dict
    
