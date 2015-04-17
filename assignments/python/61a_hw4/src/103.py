def num_common_letters(goal_word, guess):
    gw=get_list(goal_word)
    gu=list(set(get_list(guess)))
    count=0
    for i in range(len(gw)):
        for n in range(len(gu)):
            if gw[i] == gu[n]:
                count = count+1
    return count
