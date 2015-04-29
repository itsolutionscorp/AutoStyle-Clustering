from string import ascii_uppercase

def hey(s):
    if len(s.strip()) == 0:
        return "Fine. Be that way!"

    some_letters = any([(i in ascii_uppercase) for i in s])
    if s == s.upper() and some_letters:
        return "Whoa, chill out!"

    last = s[-1]
    if last == "?":
        return "Sure."

    return "Whatever."
