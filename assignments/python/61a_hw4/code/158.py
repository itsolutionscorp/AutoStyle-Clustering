def num_common_letters(goal_word, guess):
    guess_string = get_string(guess)
    goal_string = get_string(goal_word)
    y = 0
    for i in goal_string:
        for j in guess_string:
            if i == j:
                y = y+1
    return y
