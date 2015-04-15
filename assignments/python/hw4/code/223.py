def num_common_letters(goal_word, guess):
    common_letters = 0
    f = 0 # for each iin get_string(guess) loop
    word = get_list(guess)
    goal = get_list(goal_word)
    for x in goal:
        for y in word:
            if x == y:
                common_letters += 1
                break
    return common_letters
