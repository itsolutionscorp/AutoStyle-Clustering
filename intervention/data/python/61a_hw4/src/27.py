def num_common_letters(goal_word, guess):
    y = get_list(guess)
    x = get_list(goal_word)
    varse = []
    for elements in x:
        for element in y:
            if element == elements:
                if elements not in varse:
                    varse += [elements]
    return len(varse)     
