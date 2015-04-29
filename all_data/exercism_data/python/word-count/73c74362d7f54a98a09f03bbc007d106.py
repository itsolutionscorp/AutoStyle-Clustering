def word_count(words):
    wrd_list=wrd_list=words.split()
    wrd_dict={}
    for i in range(len(wrd_list)):
        if wrd_list[i] in wrd_dict:
            wrd_dict[wrd_list[i]]+=1
        else: wrd_dict[wrd_list[i]]=1

    return wrd_dict
