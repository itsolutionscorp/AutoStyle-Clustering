#
# Skeleton file for the Python "Bob" exercise.
#


def hey(phrase):
    return ['Whatever.', 'Whoa, chill out!', 'Sure.', 'Fine. Be that way!'][
        sum(((phrase.strip() == ''),)) * 3 or
        sum((any(c.isalpha() for c in phrase) and not any(
            c.islower() for c in phrase),)) or
        sum((phrase.endswith('?'),)) * 2]
