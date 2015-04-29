def to_rna(DNA):
	transcribe = {'A':'U','C':'G','G':'C','T':'A'}
	RNA = ""
	for i in range(len(DNA)):
		RNA += transcribe[DNA[i].upper()]
	return RNA
