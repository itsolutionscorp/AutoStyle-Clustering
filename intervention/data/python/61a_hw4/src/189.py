def num_common_letters(goal_word, guess):
    goal = get_list(goal_word)
    guess_word = get_list(guess)
    counter = 0 
    for element in goal: 
        if element in guess_word: 
            counter += 1 
    return counter 
        
