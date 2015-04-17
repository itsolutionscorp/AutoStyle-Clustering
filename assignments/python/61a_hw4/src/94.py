def num_common_letters(goal_word, guess):
    lst1 = get_list(goal_word)
    lst2 = get_list(guess)
    z= 0 
    for i in lst1:
        if i in lst2:
            z += 1
    return z
