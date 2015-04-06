def num_common_letters(goal_word, guess):
    lst_goal, lst_guess = get_list(goal_word), get_list(guess)
    lst_common_letters = []
    for l in lst_guess:
        if l in lst_goal and l not in lst_common_letters:
            lst_common_letters += [l]
    return len(lst_common_letters)
