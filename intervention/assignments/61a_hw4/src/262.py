def num_common_letters(goal_word, guess):
    total = 0
    repeated_letters = []
    for elem_goal in get_list(goal_word):
        for elem_guess in get_list(guess):
            if elem_goal == elem_guess and elem_guess not in repeated_letters:
                total = total + 1
                repeated_letters += [elem_guess]
    return total 
