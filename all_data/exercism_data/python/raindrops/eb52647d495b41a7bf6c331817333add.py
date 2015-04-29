def raindrops(number):
    """Return number in raindrop-speak."""
    text = ""
    if not number % 3:
        text += "Pling"
    if not number % 5:
        text += "Plang"
    if not number % 7:
        text += "Plong"
    if not text:
        text = str(number)
    return text
