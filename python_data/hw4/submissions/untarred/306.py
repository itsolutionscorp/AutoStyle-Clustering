def num_common_letters(goal_word, guess):
    count=0
    list=[]
    a=get_list(guess)
    b=get_list(goal_word)
    for char1 in a:
        for char2 in b:
            if char1==char2 and char1 not in list:
                count+=1
                list+=[char1]
    return count
