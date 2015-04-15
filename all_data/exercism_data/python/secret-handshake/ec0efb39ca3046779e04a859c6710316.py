COMMANDS = [
    'wink',
    'double blink',
    'close your eyes',
    'jump'
]

def handshake(code_pattern):
    code_pattern = __parse(code_pattern)
    message = [command for i, command in enumerate(COMMANDS) if __in_list(code_pattern, i)]
    if __in_list(code_pattern, 4):
        return list(reversed(message))
    return message

def code(messages):
    for message in messages:
        if message not in COMMANDS:
            return '0'
    if not __in_order(messages):
        messages = list(reversed(messages))
        return '1' + __encoded_messages(messages)
    return __encoded_messages(messages)

def __in_list(command, i):
    return command & (2 ** i) != 0

def __parse(code_pattern):
    if code_pattern < 0:
        code_pattern = 0
    if isinstance(code_pattern, str):
        if not (__is_valid_binary(code_pattern)):
            code_pattern = 0
        else:
            code_pattern = int(code_pattern, 2)
    return code_pattern

def __in_order(messages):
    indices = [COMMANDS.index(message) for message in messages]
    return sorted(indices) == indices

def __encoded_messages(messages):
    return format(sum([(2 ** i) for i, command in enumerate(COMMANDS) if command in messages]), 'b')

def __is_valid_binary(code_pattern):
    return all(n in ['0', '1'] for n in code_pattern)
