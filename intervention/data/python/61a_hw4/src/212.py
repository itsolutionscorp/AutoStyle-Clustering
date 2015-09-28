def num_common_letters(goal_word, guess):
    total=0
    for i in range(len(get_list(guess))):
        if get_list(guess)[i] in get_list(goal_word):
            if get_list(guess)[i] in get_list(guess)[1:]:
                total-=1 
            else: 
                total+=1
    return total
           
