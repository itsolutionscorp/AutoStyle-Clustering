DNA = "GATGGAACTTGACTACGTAAATT"

DNAList = list(DNA)


def complement ( l ):
	if l == "G":
		return "C"
	elif l == "C":
		return "G"
	elif l == "T":
		return "A"
	elif l == "A":
		return "U"
	else:
		print "Strand contains false letter."


for l in DNAList:
	print  "'%s' -> '%s'" % (l , complement( l ))
