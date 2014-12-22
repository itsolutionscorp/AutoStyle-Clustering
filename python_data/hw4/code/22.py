def num_common_letters(goal_word, guess):
    list1,list2=get_list(goal_word),get_list(guess)
    n=0
    for l1 in list1:
        k=1
        while k<=len(list2):
            if l1==list2[k-1]:
                n+=1
                break
            k+=1
    return n
