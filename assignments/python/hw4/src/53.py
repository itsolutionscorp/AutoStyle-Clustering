def num_common_letters(goal_word, guess):
    goal_word_list = get_list(goal_word)
    guess_word_list = get_list(guess)
    already_counted = []
    num_common =0
    for letter1 in goal_word_list:
        for letter2 in guess_word_list:
            if (letter1 == letter2) and (letter1 not in already_counted):
                num_common += 1
                already_counted += letter1
    return num_common
            
