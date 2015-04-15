def add_gigasecond(dt):

    from datetime import date
    
    # Idea is to convert date to number, add 10^9 / 86400 sec/day
    # and convert back to date. This exploits that integer division 
    # ignores remainder. 

    dt_ord = dt.toordinal()
    giga_dt = date.fromordinal(dt_ord + 10**9 / 86400)

    return giga_dt
