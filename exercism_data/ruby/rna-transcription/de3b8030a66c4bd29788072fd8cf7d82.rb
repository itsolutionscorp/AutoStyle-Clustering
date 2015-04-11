class Complement
	NUCLEOTIDE_MAP = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

	def self.of_dna(seq)
		complement = ''
		seq.each_char { |elm| complement << NUCLEOTIDE_MAP[elm] }
		complement
	end
	def self.of_rna(seq)
		complement = ''
		seq.each_char { |elm| complement << NUCLEOTIDE_MAP.key(elm) }
		complement
	end
end
