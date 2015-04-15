EVENTS = ('wink',
          'double blink',
          'close your eyes',
          'jump')


def handshake(number):
    if isinstance(number, str):
        try:
            number = int(number, base=2)
        except:
            number = 0

    if number <= 0:
        return []

    result = []
    for i, event in enumerate(EVENTS):
        if number & (1 << i):
            result.append(event)
    if number & 0b10000:
        result.reverse()

    return result


def code(events):
    number = 0
    for event in events:
        try:
            number += (1 << EVENTS.index(event))
        except ValueError:
            return "0"

    # test whether the order of events is reversed
    if len(events) >= 2 and EVENTS.index(events[0]) > EVENTS.index(events[1]):
        number += 0b10000

    return bin(number)[2:]
