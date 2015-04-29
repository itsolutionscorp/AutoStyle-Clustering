plain = "abcdefghijklmnopqrstuvwxyz1234567890"
cipher = "zyxwvutsrqponmlkjihgfedcba1234567890"

def encode(src):
    
    src = src.lower().replace(" ", "").replace(".", "").replace(",", "")
    tgt = ""
    tgt2 = "" 
    
    for c in src:
        
        i = plain.find(c)
        tgt += cipher[i]
    
    n_whitespace = (len(src)-1) / 5
    
    if n_whitespace == 0:
        
        tgt2 = tgt
        
    else:
        for i in range(n_whitespace):
            tgt2 += tgt[i*5:i*5+5] + " "
        
        tgt2 += tgt[i*5+5:]
                
    return tgt2

def decode(src):
    
    src = src.lower().replace(" ", "")
    tgt = ""
    
    for c in src:
        
        i = cipher.find(c)
        tgt += plain[i]
        
    return tgt
