def num_common_letters(goal_word, guess):
    goal_list=get_list(goal_word)
    guess_list=get_list(guess)
    num_common=0
    for i in goal_list:
        for j in guess_list:
            if i==j:
                guess_list.remove(j)
                num_common+=1
    return num_common
