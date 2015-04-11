def is_leap_year(entry):
    if entry %4==0:
        if entry %100==0 and entry %400!=0:
            return False
        else: 
            return True
    else: 
        return False
        
