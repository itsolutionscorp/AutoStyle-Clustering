def num_common_letters(goal_word, guess):
    goal_word_list, guess_list, count = get_list(goal_word), get_list(guess), 0
    stripped_guess_list = []
    for elem in guess_list:
        if not (elem in stripped_guess_list):
            stripped_guess_list += [elem]
    for elem_goal in goal_word_list:
        for elem_guess in stripped_guess_list:
            if elem_guess == elem_goal:
                count += 1
    return count
