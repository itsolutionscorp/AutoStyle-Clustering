def num_common_letters(goal_word, guess):
    goal_word_list = get_list(goal_word)
    guess_list = get_list(guess)
    commonChars = 0
    for char in goal_word_list:
        if char in guess_list:  
            commonChars += 1
    return commonChars
