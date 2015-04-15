# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`

def RNA(rna):
	if rna=="G":
		return "C"
	elif rna=="C":
		return "G"
	elif rna=="T":
		return "A"
	else:
		return "U"

def to_rna(rna):
	if len(rna)==1:
		return RNA(rna)

	else:
		return ''.join(map(RNA,[i for i in rna]))
