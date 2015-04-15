def score(word, multiplier=1):
    score_dict = {
        1: ['a', 'e', 'i', 'o', 'u', 'l', 'n', 'r', 's', 't'],
        2: ['d', 'g'],
        3: ['b', 'c', 'm', 'p'],
        4: ['f', 'h', 'v', 'w', 'y'],
        5: ['k'],
        8: ['j', 'x'],
        10: ['q', 'z'],
    }
    total_score = 0

    for letter in word.lower():
        if ord(letter) < 123 > 96:
            for key, chars in score_dict.items():
                if letter in chars:
                    total_score += key
    #added multiplier in case double / triple word was added.
    return total_score * multiplier
