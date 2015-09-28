def num_common_letters(goal_word, guess):
    string_goal_word = get_string(goal_word)
    string_guess = set(get_string(guess))
    sim = 0
    for letter in string_goal_word:
        for guess_letter in string_guess:
            
            if letter == guess_letter:
                sim += 1
    return sim
