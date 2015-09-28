def num_common_letters(goal_word, guess):
    count = 0
    so_far = []
    for n in get_list(goal_word):
        for m in get_list(guess):
            if m == n:
                if m not in so_far:
                    count += 1
                so_far += [m]
    return count
