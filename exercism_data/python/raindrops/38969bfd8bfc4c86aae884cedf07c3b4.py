DROPS = [3, 5, 7]
CONVERSIONS = ['Pling', 'Plang', 'Plong']

def raindrops(n):
    song = [CONVERSIONS[DROPS.index(drop)] for drop in DROPS if n % drop == 0]
    if not song:
        return str(n)
    return str.join('', song)
