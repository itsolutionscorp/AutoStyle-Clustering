def trinary(digits):
    try:
        return int(digits, base=3)
    except ValueError:
        return 0
