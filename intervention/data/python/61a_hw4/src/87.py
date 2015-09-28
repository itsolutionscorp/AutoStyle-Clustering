def num_common_letters(goal_word, guess):
    letter_set = set(get_list(guess))
    count = 0
    for letter in letter_set:
        if letter in get_list(goal_word):
            count += 1
    return count
