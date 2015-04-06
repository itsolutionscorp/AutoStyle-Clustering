def num_common_letters(goal_word, guess):
    total = 0
    for item in goal_word:
        matched = False
        for letter in guess: 
            if item == letter and not matched:
                total += 1
                matched = True
    return total
