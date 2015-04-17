def num_common_letters(goal_word, guess):
    inCommon=0
    for i in get_list(goal_word):
        if i in get_list(guess):
            inCommon+=1
            
    return inCommon
