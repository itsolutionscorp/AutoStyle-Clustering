def num_common_letters(goal_word, guess):
    new_guess = get_list(guess)
    new_goal = get_list(goal_word)
    common_letters = []
    for i in new_goal:
        for x in new_guess:
            if i == x:
                if x not in common_letters:
                    common_letters += [x]
    return len(common_letters)
