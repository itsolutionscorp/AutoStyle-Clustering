# Attempt to succeed test without if statements        
def hey(quote):
    response = {0:'Whatever.',1:'Whoa, chill out!',2:'Sure.',3:'Fine. Be that way!'}
    a = response[1*quote.isupper() or 2*quote.endswith('?') or 3*(not quote.split()) or 0]
    return unicode(a)
    
