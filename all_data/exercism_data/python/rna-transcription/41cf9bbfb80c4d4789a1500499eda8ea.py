from string import maketrans
#technically this is wrong
#transcribing DNA to RNA flips the direction around
#so for example "ATG" transcribes to "CAU"
def to_rna(seq):
	trans_table = maketrans("ATCG","UAGC") 
	return seq.translate(trans_table)
