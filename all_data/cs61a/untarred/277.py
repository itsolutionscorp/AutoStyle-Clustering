def num_common_letters(goal_word, guess):
    g_w, g = get_list(goal_word), list(set(get_list(guess)))
    l_g = len(g)
    l_gw = len(goal_word)
    s_l = 0                         #same letters
    while l_g > 0:
        n = 0
        while n <= l_gw - 1:
            if g[l_g -1] == g_w[n]:
                s_l += 1
                n += 1
            else:
                n += 1 
        l_g -= 1
    return s_l
