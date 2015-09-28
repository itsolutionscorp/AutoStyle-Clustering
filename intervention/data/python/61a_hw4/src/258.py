def num_common_letters(goal_word, guess):
    goal = get_string(goal_word)
    given = get_string(guess)
    n = len(goal)
    i = 0
    k = 0
    while i<n:
        if goal[i] in given:
            k = k+1
        i = i+1
    return k
