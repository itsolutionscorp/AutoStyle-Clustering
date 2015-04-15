import re

def parse_binary(binary):
    if _is_invalid(binary):
        raise ValueError

    total = 0
    for i, char in enumerate(reversed(binary)):
        total += (2 ** i) * int(char)
    return total

def _is_invalid(binary):
    return re.search(r"\D|[2..9]", binary)
