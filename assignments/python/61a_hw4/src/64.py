def num_common_letters(goal_word, guess):
    count = 0
    for letter in goal_word:
        for i in guess:
            if letter == i:
                count += 1
                break
    print(count)
