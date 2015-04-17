def num_common_letters(goal_word, guess):
    i = 0
    lst = []
    while i < len(get_list(goal_word)):
        if get_list(guess)[i] in get_list(goal_word) and \
        get_list(guess)[i] not in lst:
            lst += get_list(guess)[i]
        i += 1
    return len(lst)
