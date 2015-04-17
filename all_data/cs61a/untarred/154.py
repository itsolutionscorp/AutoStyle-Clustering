def num_common_letters(goal_word, guess):
    num_common_letters = []
    i = 0
    while i < len(get_list(goal_word)):
        if get_list(guess)[i] in get_list(goal_word) and get_list(guess)[i] not in num_common_letters:
            num_common_letters += get_list(guess)[i]
        i += 1
    return len(num_common_letters)
