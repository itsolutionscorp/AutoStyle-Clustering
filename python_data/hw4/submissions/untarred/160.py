def num_common_letters(goal_word, guess):
    goal_words = get_list(goal_word)
    guess = get_list(guess)
    a = 0
    b = 0
    total = 0
    match = False
    while a < len(goal_words):
        while b < len(guess):
            if goal_words[a] == guess[b]:
                match = True
            b += 1
        b = 0
        if match:
            total += 1
        match = False
        a += 1
    return total
