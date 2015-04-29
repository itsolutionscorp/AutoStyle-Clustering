# Lookup table for roman numerals
ROMANS = {
	0: "",
	1 : "I",
	2: "II",
	3: "III", 
	4: "IV", 
	5: "V",
	6: "VI",
	7: "VII",
	8: "VIII",
	9: "IX",
	10: "X"}

def numeral(arabic):
	# Separate thousands, hundreds, tens and ones
	thousands = int(arabic / 1000)
	hundreds = int(arabic % 1000 / 100)
	tens = int(arabic % 100 / 10)
	ones = int(arabic % 10)

	# Construct roman numeral string
	# Thousands
	roman = ""
	roman += "M" * thousands
	
	# Hundreds
	temp = ROMANS[hundreds].replace("I", "C")
	temp = temp.replace("V", "D")
	temp = temp.replace("X", "M")
	roman += temp

	# Tens
	temp = ROMANS[tens].replace("X", "C")
	temp = temp.replace("V", "L")
	temp = temp.replace("I", "X")
	roman += temp

	# Ones
	roman += ROMANS[ones]

	return(roman)
