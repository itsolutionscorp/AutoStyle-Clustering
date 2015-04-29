"""Rain drops."""


def divisible(x, y):
    """Return true if the 1st argument is divisible by the 2nd argument."""
    return x % y == 0


# A mapping from a factor to a rain drop.
factor_to_raindrop = ((3, "Pling"), (5, "Plang"), (7, "Plong"))


def raindrops(number):
    """Convert a number to rain drops."""
    drops = [
        raindrop
        for factor, raindrop in factor_to_raindrop
        if divisible(number, factor)
    ]
    return "".join(drops) if drops else str(number)
