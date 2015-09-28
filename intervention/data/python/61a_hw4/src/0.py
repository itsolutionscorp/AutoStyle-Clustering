def num_common_letters(goal_word, guess):
    same_letters_lst = []
    for el in goal_word:
        for letter in guess:
            if letter == el:
                same_letters_lst += letter
    return len(set(same_letters_lst))
