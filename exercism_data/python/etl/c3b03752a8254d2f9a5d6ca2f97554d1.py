

def transform(old):
    expected = {}
    for val, lst in old.items():
        for item in lst:
            expected.update({item.lower(): val})

    return expected
