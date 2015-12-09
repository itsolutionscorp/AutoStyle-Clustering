def num_common_letters(goal_word, guess):
    count = 0
    list_of_used_letters = list()
    for letter in goal_word:
        if letter in guess and letter not in list_of_used_letters:
            count += 1
            list_of_used_letters += letter
    return count