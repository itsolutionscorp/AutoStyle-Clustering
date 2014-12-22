def num_common_letters(goal_word, guess):
    gwstr = get_string(goal_word)
    guestr = get_string(guess)
    ncl = 0
    for i in range(len(gwstr)):
        if gwstr[i] in guestr:
            ncl += 1
    return ncl
