import re

def Binary(binary):
    if _is_invalid(binary):
        return 0

    total = 0
    for i, char in enumerate(reversed(binary)):
        total += (2 ** i) * int(char)
    return total

def _is_invalid(binary):
    return re.search(r"\D", binary)
