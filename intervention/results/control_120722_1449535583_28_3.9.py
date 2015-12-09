def num_common_letters(goal_word, guess):
    list_of_used_letters = [letter for letter in goal_word if letter in guess]
    return len(list(set(list_of_used_letters)))