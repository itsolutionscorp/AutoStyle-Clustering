def num_common_letters(goal_word, guess):
    count = 0
    seen_chars = []
    for char in goal_word:
        if char not in seen_chars:
            if char in guess:
                count += 1
            seen_chars.append(char)
    return count
