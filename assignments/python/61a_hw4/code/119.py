def num_common_letters(goal_word, guess):
    total = 0
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    for elem in goal_list:
        mult = 0
        for elem2 in guess:
            if elem == elem2 and mult == 0:
                total += 1
                mult = 1
    return total
