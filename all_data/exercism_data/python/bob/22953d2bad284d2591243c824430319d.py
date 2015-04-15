def hey(s):
    c = s.strip()
    if c == ""        : return "Fine. Be that way!"
    if c.isupper()    : return "Woah, chill out!"
    if c.endswith("?"): return "Sure."
    return "Whatever."

if __name__ == "__main__":
    import sys
    for s in sys.argv[1:]: print hey(s)
