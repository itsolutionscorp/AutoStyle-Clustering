def word_count(input_string):
    word_list=input_string.split()
    word_count=[]
    for word_outer in word_list:
        count=0
        for word_inner in word_list:
            if word_inner==word_outer:
                count+=1
        word_count+=[count]
    return dict(zip(word_list,word_count))
