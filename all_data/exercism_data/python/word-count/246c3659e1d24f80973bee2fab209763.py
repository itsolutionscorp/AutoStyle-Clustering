def word_count(input):
    words = input.split()

    count = {}

    for str in words:
        if str  in count:
            count[str] += 1
        else:
            count[str] = 1

    return count
