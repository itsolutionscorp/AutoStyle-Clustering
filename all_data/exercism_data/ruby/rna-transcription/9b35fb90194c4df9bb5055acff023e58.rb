module Complement
	
	def self.of_dna(dna_complement1)
		dna_complement2 = ""
		dna_pair_hash = {"T" => "A", "C" => "G", "G" => "C", "A" => "U"}

		dna_complement1.each_char do |protein|
			dna_complement2.concat(dna_pair_hash[protein])
		end
		return dna_complement2
	end

	def self.of_rna(rna_complement1)
		rna_complement2 = ""
		rna_pair_hash = {"U" => "A", "C" => "G", "G" => "C", "A" => "T"}

		rna_complement1.each_char do |protein|
			rna_complement2.concat(rna_pair_hash[protein])
		end
		return rna_complement2
	end

end
