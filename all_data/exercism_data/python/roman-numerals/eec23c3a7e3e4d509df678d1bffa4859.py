numerals = {
    0: { # 10^0, ones
        1: "I",
        5: "V"
    },
    1: { # 10^1, tens
        1: "X",
        5: "L"
    },
    2: { # 10^2, hundreds
        1: "C",
        5: "D"
    },
    3: { # 10^3, thousands
        1: "M",
        5: ""
    }
}

def numeral(arabic):
    roman = ""

    for i in range(3, -1, -1):
        curr_value = arabic / 10**i
        if curr_value == 0:
            continue

        subtraction = curr_value

        if (curr_value + 1) % 5 == 0:
            if curr_value == 4:
                roman += numerals[i][1] + numerals[i][5]
            elif curr_value == 9:
                roman += numerals[i][1] + numerals[i+1][1]
        else:
            if curr_value >= 5:
                roman += numerals[i][5]
                curr_value -= 5
            roman += numerals[i][1] * curr_value

        arabic -= 10**i * subtraction

    return roman
