#bob

import re
def hey(msg):
        
    if (msg.strip()==""):
        return "Fine. Be that way!"
    if (re.search("[A-Z]+",msg) and msg.isupper()):
        return "Whoa, chill out!"
    if (msg.strip().endswith("?")):
        return "Sure."
    return "Whatever."    
        
     
