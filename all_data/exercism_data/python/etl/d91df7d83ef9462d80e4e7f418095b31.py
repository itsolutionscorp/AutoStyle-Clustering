def transform(old):
    return {char.lower(): value
            for value, chars in old.items() for char in chars}
