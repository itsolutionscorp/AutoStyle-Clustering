def num_common_letters(goal_word, guess):
    guess_letters = get_list(guess)
    nocopy_guess_letters = []
    goal_letters = get_list(goal_word)
    counter = 0
    for i in range(len(guess_letters)):
        if guess_letters[i] not in (guess_letters[:i]):
            nocopy_guess_letters += [guess_letters[i]]
    for letter in nocopy_guess_letters:
        if letter in goal_letters:
            counter += 1
    return counter
