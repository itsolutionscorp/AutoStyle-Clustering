def num_common_letters(goal_word, guess):
    l1, l2 = get_list(goal_word), get_list(guess)
    total=0
    for letter in l1:
        if letter in l2:
            total+=1
    return total
