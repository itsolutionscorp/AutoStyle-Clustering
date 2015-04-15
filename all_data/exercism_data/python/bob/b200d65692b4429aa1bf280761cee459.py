# Exercism for 9/23/14
# bob.py


def hey(msg):
    if not msg or msg.isspace():
        return "Fine. Be that way!"
    elif msg.isupper():
        return "Whoa, chill out!"
    elif msg.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
