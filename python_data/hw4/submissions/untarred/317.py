def num_common_letters(goal_word, guess):
    total = 0
    for e in get_list(goal_word):
        i = 0
        while i < len(get_list(guess)):
            if e == get_list(guess)[i]:
                total+=1
                i = len(get_list(guess))
            i += 1
    return total
