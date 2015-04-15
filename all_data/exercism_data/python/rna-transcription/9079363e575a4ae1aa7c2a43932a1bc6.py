def to_rna(sequence):
	rna_sequence = []
	transcription = ""
	for element in sequence:
		if element == "G":
			transcription = "C"
		elif element == "C":
			transcription = "G"
		elif element == "T":
			transcription = "A"
		elif element == "A":
			transcription = "U"
		rna_sequence.append(transcription)
	return "".join(rna_sequence)
