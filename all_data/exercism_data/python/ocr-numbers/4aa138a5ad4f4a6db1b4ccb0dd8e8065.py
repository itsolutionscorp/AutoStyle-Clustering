OCR_DISP = [[
    " _ ",
    "| |",
    "|_|",
    "   "
],[
    "   ",
    "  |",
    "  |",
    "   "
],[
    " _ ",
    " _|",
    "|_ ",
    "   "
],[
    " _ ",
    " _|",
    " _|",
    "   "
],[
    " _ ",
    "| |",
    "|_|",
    "   "
],[
    " _ ",
    "|_ ",
    " _|",
    "   "
],[
    " _ ",
    "|_ ",
    "|_|",
    "   "
],[
    " _ ",
    "  |",
    "  |",
    "   "
],[
    " _ ",
    "|_|",
    "|_|",
    "   "
],[
    " _ ",
    "|_|",
    "  |",
    "   "
]]

def grid(number):
    return OCR_DISP[int(number)]

def number(grid):
    if len(grid) != 4 or any([len(row) != 3 for row in grid]):
        raise ValueError("Grid is not 3x4.")
    if not grid in OCR_DISP:
        return "?"
    else:
        return str(OCR_DISP.index(grid))
