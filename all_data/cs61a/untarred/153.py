def num_common_letters(goal_word, guess):
    how_many_basic_beaches = 0
    goal = get_list(goal_word)
    the_guess = get_list(guess)
    while len(goal) > 0 :
        if goal[0] in the_guess:
            how_many_basic_beaches += 1
        goal = goal[1:]
    return how_many_basic_beaches
