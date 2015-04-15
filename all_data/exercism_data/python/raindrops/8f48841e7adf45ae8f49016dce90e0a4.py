"""Rain drops."""


def divisible(x, y):
    """Return true if the 1st argument is divisible by the 2nd argument."""
    return x % y == 0


def raindrops(number):
    """Convert a number to rain drops."""
    drops = []
    if divisible(number, 3):
        drops.append("Pling")
    if divisible(number, 5):
        drops.append("Plang")
    if divisible(number, 7):
        drops.append("Plong")
    if ((not divisible(number, 3)) and (not divisible(number, 5)) and
            (not divisible(number, 7))):
        drops.append(str(number))
    return "".join(drops)
