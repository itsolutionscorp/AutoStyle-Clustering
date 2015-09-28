def num_common_letters(goal_word, guess):
    goal_word = get_list(goal_word)
    guess = get_list(guess)
    total = 0
    for letters in goal_word:
        found_match = False 
        for match in guess:
            if match == letters and found_match==False:
                found_match = True
                total+=1
    return total            
    
