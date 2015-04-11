def raindrops(n):
    return "".join(s for mod, s in [(3, "Pling"), (5, "Plang"), (7, "Plong")]
                   if n % mod == 0) or str(n)
