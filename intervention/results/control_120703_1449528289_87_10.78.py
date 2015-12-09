def num_common_letters(goal_word, guess):
    count = 0
    word = list(guess)
    for letter in guess:
        if word.count(letter) > 1:
            word.remove(letter)
        elif letter in goal_word:
            count += 1
    return count
