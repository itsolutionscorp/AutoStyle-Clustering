class InvalidCodonError < StandardError; end

class Translation
	@@codons = 
	{
		'AUG' => 'Methionine',

		'UUU' => 'Phenylalanine',
		'UUC' => 'Phenylalanine',

		'UUA' => 'Leucine', 
		'UUG' => 'Leucine',

		'UCU' => 'Serine',
		'UCC' => 'Serine', 
		'UCA' => 'Serine', 
		'UCG' => 'Serine',

		'UAU' => 'Tyrosine',
		'UAC' => 'Tyrosine',

		'UGU' => 'Cystine',
		'UGC' => 'Cystine',

		'UGG' => 'Tryptophan',

		'UAA' => 'STOP',
		'UAG' => 'STOP',
		'UGA' => 'STOP',
	}
	
	def self.of_codon(codon)
		raise(InvalidCodonError, 'Invalid codon was detected.') unless @@codons.key?(codon)
		@@codons[codon]
	end
	
	def self.of_rna(rna)
		rna.scan(/.{3}/)
			.collect{|c| of_codon(c)}
			.take_while{|p| p != 'STOP'}
	end
end
