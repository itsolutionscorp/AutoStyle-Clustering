def num_common_letters(goal_word, guess):
    new_list=[]
    goal_word=get_string(goal_word)
    guess=get_string(guess)
    counter=0
    for i in goal_word:
        if i in guess and i not in new_list:
            counter+=1
            new_list+=[i]
    return counter
