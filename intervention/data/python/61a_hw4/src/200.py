def num_common_letters(goal_word, guess):
    temp1=get_list(goal_word)
    temp2=get_list(guess)
    counter=0
    for el in temp1:
        for els in temp2:
            if(el==els):
                counter+=1
                break
    return counter
