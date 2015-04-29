class Complement

	CONVERSION_HASH = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

	def self.of_dna dna
		dna.chars.collect{ |c| CONVERSION_HASH[c] }.join
	end

	def self.of_rna rna
		rna.chars.collect{ |c| CONVERSION_HASH.find { |k, v| v == c }.first }.join
	end

end
