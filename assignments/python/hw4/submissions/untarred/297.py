def num_common_letters(goal_word, guess):
    list1 = get_string(goal_word)
    list2 = get_string(guess)
    common = 0
    for i in range(len(list1)):
        if list1[i] in list2:
            common += 1
    return common
