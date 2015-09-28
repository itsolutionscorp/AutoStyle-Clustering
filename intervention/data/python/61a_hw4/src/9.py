def num_common_letters(goal_word, guess):
    goal_temp = get_string(goal_word)
    guess_temp = get_string(guess)
    x=0
    for c in goal_temp:
        for s in guess_temp:
            if c == s:
                x = x+1
    return x
