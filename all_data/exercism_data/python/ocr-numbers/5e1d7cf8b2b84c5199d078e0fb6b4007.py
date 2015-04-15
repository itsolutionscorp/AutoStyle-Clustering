"""OCR"""

#: a mapping from digits to glyphs
DIGIT_TO_GLYPH = [
    [" _ ",
     "| |",
     "|_|",
     "   "],
    ["   ",
     "  |",
     "  |",
     "   "]
]

GLYPH_WIDTH, GLYPH_HEIGHT = (3, 4)


def number(glyph):
    """Return a digit that corresponds to a glyph."""
    if len(glyph) != GLYPH_HEIGHT or \
       any(len(row) != GLYPH_WIDTH for row in glyph):
        raise ValueError("Ill-formed grid")

    try:
        return str(DIGIT_TO_GLYPH.index(glyph))
    except ValueError:
        return "?"


def grid(digit):
    """Return a glyph that corresponds to a digit."""
    digit = int(digit)
    if not (0 <= digit < len(DIGIT_TO_GLYPH)):
        raise ValueError("Unknown digit")

    return DIGIT_TO_GLYPH[digit]
