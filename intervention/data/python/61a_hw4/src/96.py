def num_common_letters(goal_word, guess):
    mwfs, mwfl = make_word_from_string, make_word_from_list
    a = get_list(goal_word)
    b = get_list(guess)
    used_list = []
    i = 0
    count = 0
    while len(b) > i:
        if (b[i] in a) and not(b[i] in used_list):
            count += 1
            used_list += b[i]
        i += 1
    return count
