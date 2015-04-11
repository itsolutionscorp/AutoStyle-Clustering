from re import search


def hey(to_bob):
    is_forceful = to_bob.isupper() and search('[A-Z]', to_bob)
    is_questioning = bool(search(r'\w+\?\s*$', to_bob))
    is_silent = not to_bob.strip()

    if is_forceful:
        return 'Whoa, chill out!'
    if is_questioning:
        return 'Sure.'
    if is_silent:
        return 'Fine. Be that way!'

    return 'Whatever.'
