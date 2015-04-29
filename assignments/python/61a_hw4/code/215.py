def num_common_letters(goal_word, guess):
    goal_word = get_list(goal_word)
    guess = get_list(guess)
    commons = 0
    for x in goal_word:
        for y in guess:
            if x == y:
                commons += 1
    a = 0
    b = 1
    while a < len(guess):
        while b < len(guess):
            if guess[a] == guess[b]:
                commons -= 1
            b += 1
        a += 1
        b = a + 1
    return commons
    
