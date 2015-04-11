
from contextlib import suppress

events = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(dec_or_binary_str):
    try:
        dec = int(dec_or_binary_str, base=2)
    except ValueError:
        dec = 0
    except TypeError:
        dec = max(dec_or_binary_str, 0)

    sequence = [event
                for power, event in enumerate(events)
                if (2**power) & dec]

    if (2**len(events)) & dec:
        sequence.reverse()

    return sequence

def code(sequence):
    try:
        powers = [2**events.index(event)
                  for event in sequence]
    except ValueError:
        return '0'
    
    code = 0 if powers == sorted(powers) else 2**len(events)
    for power in powers:
        code ^= power
    return bin(code)[2:]
    
