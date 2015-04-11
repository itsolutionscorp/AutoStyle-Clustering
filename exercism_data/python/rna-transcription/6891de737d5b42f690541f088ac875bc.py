def to_rna(adnstrand):
	rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
	return ''.join([rna[adn] for adn in adnstrand])
