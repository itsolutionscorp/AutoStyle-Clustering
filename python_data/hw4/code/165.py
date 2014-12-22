def num_common_letters(goal_word, guess):
    a = get_list(goal_word)
    b = get_list(guess)
    t = 0
    for i in a:
        if i in b:
            t += 1
    return t
