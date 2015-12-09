def num_common_letters(goal_word, guess):
    count = 0
    used = list()
    for letter in goal_word:
        if letter in guess and letter not in used:
            count += 1
            used += letter
    return count
			