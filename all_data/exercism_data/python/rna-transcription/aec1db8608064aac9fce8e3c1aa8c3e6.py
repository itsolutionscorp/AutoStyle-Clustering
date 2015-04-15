# converts a strand of DNA into its RNA complement
# strand must be of type str, consisting only of characters A, C, G, and T.
def to_rna(strand):
	return strand.replace('G','X').replace('C','G').replace('X','C').replace('A','U').replace('T','A')
