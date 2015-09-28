def num_common_letters(goal_word, guess):
    final, new_lst = 0, []
    for elem in goal_word:
        for letter in guess:
            if elem == letter:
                new_lst += [elem]
    return len(set(new_lst))
