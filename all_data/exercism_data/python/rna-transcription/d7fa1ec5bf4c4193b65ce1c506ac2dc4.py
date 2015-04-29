def to_rna(sequence):
	return sequence.replace("A","U")  \
									.replace("T","A") \
									.replace("C"," ") \
									.replace("G","C") \
									.replace(" ","G") \
