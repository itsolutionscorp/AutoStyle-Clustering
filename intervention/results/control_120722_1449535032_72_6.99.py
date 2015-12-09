def num_common_letters(goal_word, guess):
    list_of_used_letters = []
    for letter in goal_word:
        if letter in guess and letter not in list_of_used_letters:
            list_of_used_letters.append(letter)
    return len(list_of_used_letters)