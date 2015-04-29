import string

def to_rna(input_strand):
	trans = str.maketrans('ATCG','UAGC');
	return (input_strand.translate(trans));
	
