def num_common_letters(goal_word, guess):
    test = get_list(guess)
    goal = get_list(goal_word)
    count = 0
    for c in goal:
        if c in test:
            count += 1
    return count
