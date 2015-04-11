def to_rna(sequence):
	# To transcribe a DNA sequence to RNA, perform the following substitutions:
	#   - G to C
	#   - C to G
	#   - T to A
	#   - A to U
	
	# A transcribed sequence.
	transcription = ''
	
	# Iterate over the sequence, transcribing as we go.
	for nucleotide in sequence:
		if nucleotide == 'G': transcription = transcription + 'C'
		if nucleotide == 'C': transcription = transcription + 'G'
		if nucleotide == 'T': transcription = transcription + 'A'
		if nucleotide == 'A': transcription = transcription + 'U'
	
	# Return the transcription.
	return transcription
