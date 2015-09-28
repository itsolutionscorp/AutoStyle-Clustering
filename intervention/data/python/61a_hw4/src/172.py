def num_common_letters(goal_word, guess):
    goal_string, guess_string = get_string(goal_word), get_string(guess)
    i, common = 0, []
    for elem in guess_string:
        if elem in goal_string and elem not in common:
            common += elem
            i += 1
    return i 
