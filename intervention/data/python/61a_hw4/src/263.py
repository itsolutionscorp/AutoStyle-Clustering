def num_common_letters(goal_word, guess):
    count = 0
    x = get_list(goal_word)
    y = get_list(guess)
    for chr in x:
        for chrg in y:
            if chr == chrg:
                count += 1
                y.remove(chr)
                break
    return count
