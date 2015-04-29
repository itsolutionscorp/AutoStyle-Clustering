#
# Skeleton file for the Python "Bob" exercise.
#

#Bob says
def hey(what):

    s = what.strip();

    if not s or s.isspace():
        return "Fine. Be that way!";

    if s.isupper():
        return "Whoa, chill out!";

    if s.endswith('?'):
        return "Sure.";

    return "Whatever.";


if __name__ == "__main__":
    ##Introduction to Bob
    print("Speak with Bob");

    #Be strong and speak with Bob
    #Type run to leave!
    while 1:
        ans = str(raw_input('You: '));
        print(hey(ans));
