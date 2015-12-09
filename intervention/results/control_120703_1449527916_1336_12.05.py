def num_common_letters(goal_word, guess):
    count = 0
    word = list(guess)
    for letter in guess:
        if word.count(letter) > 1:
            word.remove(letter)
    for letter in word:
        if letter in goal_word:
            count += 1
    return count