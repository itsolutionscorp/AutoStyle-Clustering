def num_common_letters(goal_word, guess):
    seen_letters = []
    counter = 0
    goal_word_list  = get_list(goal_word)
    guess_word_list = get_list(guess)
    for letter in guess_word_list:
        if letter in goal_word_list and letter not in seen_letters:
            counter = counter + 1
            seen_letters = [letter] + seen_letters
        elif letter in goal_word_list and letter in seen_letters:
            pass
        else:
            seen_letters = [letter] + seen_letters
    return counter
    
