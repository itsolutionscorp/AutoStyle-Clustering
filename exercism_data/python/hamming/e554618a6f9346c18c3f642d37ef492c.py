def distance(a, b):
    if len(a) != len(b):
        print('Size Error')
        return
    cnt = 0
    for letter_a,letter_b in zip(a,b):
        if letter_a != letter_b:
            cnt += 1
    return cnt
