events = {
    1:'wink', \
    2:'double blink', \
    4:'close your eyes', \
    8:'jump'
}

def handshake(secret):
    #Return empty list if invalid handshake value
    if secret < 1:
        return []
    if isinstance(secret,str):
        try:
            secret = int(secret,2)
        except ValueError:
            return []
    
    # Compare each binary value with event using bitwise AND
    code = [events[event] for event in sorted(events) if secret & event]
    return code if not secret & 16 else code[::-1]
    
def code(codetext):
    swap_events = dict(zip(events.values(), events.keys()))
    try:
        handshake = [swap_events[code] for code in codetext]
    except KeyError:
        return '0'
        
    return str(bin(sum(handshake)))[2::] if handshake == sorted(handshake) else \
            str(bin(sum(handshake)+16))[2::]
