def num_common_letters(goal_word, guess):
    common_letters = [] 
    letters_in_goal = get_list(goal_word)
    letters_in_guess = get_list(guess)
    for el in letters_in_goal:
        for elem in letters_in_guess:
            if elem == el and elem not in common_letters:                                
                common_letters += [elem]              
    return len(common_letters) 
