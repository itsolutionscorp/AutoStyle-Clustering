class Complement

	def self.complementing(strand,type)
		strd = ""
		nucleotides = { "G" => 'C', "C" => 'G', "T" => 'A', "U" => 'A'}
		nucleotides["A"] = if type == :dna
			'U'
		else
			'T'
		end
		strand.each_char do |s|
			strd << nucleotides[s]				
		end
		return strd
	end

	def self.of_dna(dna_strand) 
		complementing(dna_strand,:dna)
	end

	def self.of_rna(rna_strand) 
		complementing(rna_strand,:rna)
	end

end
