def num_common_letters(goal_word, guess):
    gw=get_list(goal_word)
    gu=get_list(guess)
    newgu=list(set(gu))
    same=0
    for element in gw:
        if element in newgu:
            same+=1
    return same
