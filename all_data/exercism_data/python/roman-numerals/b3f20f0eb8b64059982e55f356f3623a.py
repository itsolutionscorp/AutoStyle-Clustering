def numeral(n):
    """computes and returns a roman representation of given number"""
    romans = "M CM D CD C XC L XL X IX V IV I".split()
    arabs = [1000,900,500,400,100,90,50,40,10,9,5,4,1]
    n_to_r = dict(zip(arabs,romans))
    out = ""
    for a in arabs:
        out = "".join((out, n_to_r[a]*(n//a)) )
        n -= a*(n//a)
    return out

