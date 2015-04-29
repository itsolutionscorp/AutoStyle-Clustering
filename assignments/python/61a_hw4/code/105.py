def num_common_letters(goal_word, guess):
    goal_letters = get_list(goal_word)
    guess_letters = get_list(guess)
    common = 0
    for guess_letter in guess_letters:
        if guess_letter in goal_letters:
            common += 1
            goal_letters = [letter for letter in goal_letters if letter != guess_letter]
    return common
