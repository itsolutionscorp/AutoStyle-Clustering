CONVERSIONS = {
    3: 'Pling',
    5: 'Plang',
    7: 'Plong'
}

def raindrops(n):
    song = []
    for i in CONVERSIONS.keys():
        if n % i == 0:
            song.append(CONVERSIONS[i])

    if not song:
        return str(n)
    return str.join('', song)
