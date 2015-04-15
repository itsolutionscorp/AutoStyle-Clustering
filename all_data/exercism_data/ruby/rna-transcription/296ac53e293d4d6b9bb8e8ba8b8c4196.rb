class Complement
	@rules_hash_dna = {'G' =>'C', 'C' =>'G', 'T' =>'A', 'A' =>'U'}
	
	def self.of_dna(dna)
		dna.chars.reduce([]) do |acc, k|
			acc << @rules_hash_dna[k]
		end.join('')
	end

	def self.of_rna(rna)
		rna.chars.reduce([]) do |acc, k|
			acc << @rules_hash_dna.rassoc(k)[0]
		end.join('')
	end
end
