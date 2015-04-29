def num_common_letters(goal_word, guess):
    n = 0
    while n < len(get_list(guess)):
        if get_list(guess)[n] in get_list(guess)[n+1:]:
            del get_list(guess)[n]
        n += 1 
    k = 0
    for elem in get_list(guess): 
        if elem in (goal_word):
            k += 1
    return k    
