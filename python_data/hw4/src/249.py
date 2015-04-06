def num_common_letters(goal_word, guess):
    goal, guess, common = get_string(goal_word), get_string(guess), 0
    for letter in goal:
        if letter in guess:
            common += 1
    return common
            
