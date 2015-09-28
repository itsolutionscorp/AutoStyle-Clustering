def num_common_letters(goal_word, guess):
    k = 0
    j = []
    x1 = get_list(goal_word)
    x2 = get_list(guess)
    for i in x2:
        if i in x1 and i not in j:
            k += 1
            j += [i]
    return k
