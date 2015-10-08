def num_common_letters(goal_word, guess):
    g_word = get_list(goal_word)
    g = get_list(guess)
    count = 0
    if g == []:
        return 0
    for i in g_word:
        if i in g:
            count += 1
    return count
