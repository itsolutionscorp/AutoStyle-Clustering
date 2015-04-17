def num_common_letters(goal_word, guess):
    num_common = 0
    for letter1 in letters:
        find_goal = False
        find_guess = False
        for letter2 in goal_word:
            if letter1 == letter2:
                find_goal = True
        for letter3 in guess:
            if letter1 == letter3:
                find_guess = True
        if find_goal and find_guess:
            num_common += 1
    return num_common
