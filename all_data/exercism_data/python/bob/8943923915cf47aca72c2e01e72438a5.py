from re import search


def hey(to_bob):
    is_forceful = to_bob == to_bob.upper() and search('[A-Z]', to_bob)
    is_questioning = bool(search(r'\?\s*$', to_bob))
    is_silent = search(r'^\s*$', to_bob)

    if is_forceful:
        return 'Whoa, chill out!'
    if is_questioning:
        return 'Sure.'
    if is_silent:
        return 'Fine. Be that way!'

    return 'Whatever.'
