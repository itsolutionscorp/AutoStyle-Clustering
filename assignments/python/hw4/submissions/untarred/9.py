def num_common_letters(goal_word, guess):
    nb = 0
    checked = []
    goal_word = get_list(goal_word)
    guess = get_list(guess)
    for c in guess:
        if(not(c in checked) and (c in goal_word)):
            nb += 1
            checked += [c]
    return nb
