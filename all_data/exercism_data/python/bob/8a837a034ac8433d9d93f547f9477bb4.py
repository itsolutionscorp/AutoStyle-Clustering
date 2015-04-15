import sys

def hey(text):
    text = text.strip()
    if text == "":
        return "Fine. Be that way!"
    elif text[-1] == '?' and not text.isupper():
        return "Sure."
    elif text.isupper():
        return "Whoa, chill out!"
    else:
        return "Whatever."

if __name__ == "__main__":
    print hey(sys.argv[1])
