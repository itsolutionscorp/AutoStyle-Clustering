def transform(old):
    expected = {}
    for key in old:
        count = 0
        for item in old[key]:
            expected[old[key][count].lower()] = key
            count += 1
    return expected
