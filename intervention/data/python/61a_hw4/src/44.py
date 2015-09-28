def num_common_letters(goal_word, guess):
    count = 0
    guess = list(set(guess))
    for _ in goal_word:
        if _ in guess:   
            count +=1             
    return count
