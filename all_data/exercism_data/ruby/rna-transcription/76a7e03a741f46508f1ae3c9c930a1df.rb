class Complement

	def self.complementing(strand,type)
		strd = ""
		nucleotides = { "G" => 'C', "C" => 'G', "T" => 'A', "U" => 'A'}
		nucleotides["A"] = if type == 1
			'U'
		else
			'T'
		end

		if strand.length > 1
			strand.each_char do |s|
				strd << nucleotides[s]				
			end
		else
			strd = nucleotides[strand]
		end
		return strd
	end

	def self.of_dna(dna_strand) 
		complementing(dna_strand,1)
	end

	def self.of_rna(rna_strand) 
		complementing(rna_strand,2)
	end

end
