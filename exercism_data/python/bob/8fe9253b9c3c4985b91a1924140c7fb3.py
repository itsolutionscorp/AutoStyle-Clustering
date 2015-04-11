# List of (conditions, responses), order matters
RESPONSES = [
    (lambda m: not m or not m.strip(), 'Fine. Be that way!'),
    (lambda m: m.upper() == m, 'Woah, chill out!'),
    (lambda m: m.endswith('?'), 'Sure.'),
    (lambda m: True, 'Whatever.'),
]


class Bob:
    def hey(self, message):
        for condition, answer in RESPONSES:
            if condition(message):
                return answer
