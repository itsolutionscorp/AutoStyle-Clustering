def num_common_letters(goal_word, guess):
    goal_list = get_list(goal_word)
    guess_list = get_list(guess)
    count_of_rep = 0
    for letter in goal_list:
        if letter in guess_list:
            count_of_rep += 1
    return count_of_rep
