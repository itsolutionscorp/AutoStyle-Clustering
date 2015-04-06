def num_common_letters(goal_word, guess):
    num_cl, str = 0, ""
    for el in get_string(guess): #call gs on inputs to avoid DAV
        if el in get_string(goal_word) and el not in str:
            num_cl += 1
            str += el
    return num_cl
