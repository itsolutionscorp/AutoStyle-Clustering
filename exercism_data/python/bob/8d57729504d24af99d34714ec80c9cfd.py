#
# Skeleton file for the Python "Bob" exercise.
#

#Bob says
def hey(what):

    if not what or what.isspace():
        return "Fine. Be that way!";

    if what.isupper():
        return "Whoa, chill out!";

    if what.endswith('?') or what.endswith(' '):
        return "Sure.";

    return "Whatever.";

##Introduction to Bob
print("Speak with Bob");

#Be strong and speak with Bob
#Type run to leave!
while 1:
    ans = str(raw_input('You: '));
    print(hey(ans));
