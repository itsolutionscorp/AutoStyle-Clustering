def num_common_letters(goal_word, guess):
    g = get_string(goal_word)
    h = get_string(guess)
    counter = 0
    for x in range(len(g)):
        if g[x] in h:
            counter += 1
    return counter
           
