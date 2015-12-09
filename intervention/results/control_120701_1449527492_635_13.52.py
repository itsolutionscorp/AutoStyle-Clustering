def num_common_letters(x, y):
    i = 0
    seen_letters = []
    for letter in x:
        for y_letter in y:
            if letter == y_letter:
                if letter in seen_letters:
                    pass
                else:
                    i += 1
                    seen_letters.append(letter)
    return i
