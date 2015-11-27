def num_common_letters(goal_word, guess):
    guess_letters = []
    count= 0
    for i in guess:
        if i in goal_word:
            if i in guess_letters:
                count += 0
            else:
                count += 1
                guess_letters.append(i)
    return count