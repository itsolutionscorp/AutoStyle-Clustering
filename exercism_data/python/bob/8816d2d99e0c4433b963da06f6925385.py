# -*- coding: utf-8 -*-

def hey(address):
    address = address.strip()
    if not address:
        return 'Fine. Be that way!'
    if address.isupper():
        return 'Woah, chill out!'
    if address.endswith("?"):
        return 'Sure.'
    return "Whatever."
