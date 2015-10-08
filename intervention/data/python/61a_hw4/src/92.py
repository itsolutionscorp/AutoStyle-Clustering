def num_common_letters(goal_word, guess):
    already_guessed, common = [], 0
    for element in guess:
        if element in goal_word and element not in already_guessed:
            already_guessed += [element]
            common += 1
    return common
