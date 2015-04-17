def word_count(phrase):
    word_count_dict={}
    word_split=phrase.split()
    for word in word_split:
        if word not in word_count_dict:
            word_count_dict[word]=1
        else:
            word_count_dict[word]+=1
    return word_count_dict