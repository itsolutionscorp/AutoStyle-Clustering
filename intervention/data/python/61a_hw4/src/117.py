def num_common_letters(goal_word, guess):
    letter_used = []
    counter = 0 
    for i in (get_list(guess)): 
        for a in (get_list(goal_word)): 
            if i == a and i not in letter_used: 
                counter += 1 
                letter_used += a
    return counter 
    
