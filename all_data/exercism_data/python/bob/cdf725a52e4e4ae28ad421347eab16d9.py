def hey(communication):
    communication = communication.strip()
    reply = 'Whatever.'

    if not communication:
        reply = 'Fine. Be that way!'
    elif communication.isupper():
        reply = 'Whoa, chill out!'
    elif communication[-1] == '?':
        reply = 'Sure.'

    return reply
