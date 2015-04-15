class Complement
	def self.of_dna(nucleotide)
		# The nucleotide string can only contain some combination of the characters G, C, T, A
		raise ArgumentError unless nucleotide =~ /^[GCTA]*$/
		nucleotide.tr('GCTA', 'CGAU')
	end

	def self.of_rna(nucleotide)
		# The nucleotide string can only contain some combination of the characters C, G, A, U
		raise ArgumentError unless nucleotide =~ /^[CGAU]*$/
		nucleotide.tr('CGAU','GCTA')
	end
end
