def num_common_letters(goal_word, guess):
    counter=0
    deleted_letters = []
    goal_list=get_list(goal_word)
    guess_list=get_list(guess)
    for letters in guess_list:
        if letters in goal_list:
            if not letters in deleted_letters:
                deleted_letters += letters
                counter += 1
    return counter
