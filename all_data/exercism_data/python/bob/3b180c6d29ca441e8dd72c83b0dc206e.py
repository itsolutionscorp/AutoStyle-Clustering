def hey(input_string):
    # The Function doesn't need a very specific argument name because of how short it is
    if not input_string.strip():
        # Removes all non-letter characters and checks if the string is empty after
        return "Fine. Be that way!"
    if input_string.isupper():
        # Checks if the whole string is upper-case because that's how you yell on a computer
        return "Whoa, chill out!"
    if input_string[-1] == '?':
        # Checks if the last character is a question mark
        return "Sure."
    else:
        return "Whatever."
