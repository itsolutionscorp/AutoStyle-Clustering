def num_common_letters(goal_word, guess):  
    count = 0
    repeats = 0
    if len(set(guess)) < len(guess):
        repeats = len(guess) - len(set(guess))
    for letter1 in guess:
        for letter2 in goal_word:     
            if letter1 == letter2:
                count += 1         
    return count - repeats
