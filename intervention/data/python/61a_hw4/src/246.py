def num_common_letters(goal_word, guess):
    x = []
    for element1 in get_list(goal_word):
        for element2 in get_list(guess):
            if element1 == element2:
                if element1 not in x:
                    x = x + [element1]
    return len(x)
