def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    repeat_letters = []
    num_common = 0
    for elem in guess_list:
        if elem in goal_list and not elem in repeat_letters:
            num_common +=1
            repeat_letters += [elem]
    return num_common
