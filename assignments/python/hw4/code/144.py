def num_common_letters(goal_word, guess):
    num = 0
    list1, list2 = get_list(goal_word), get_list(guess)
    for elements in list1:
        if elements in list2:
            num += 1
    return num
