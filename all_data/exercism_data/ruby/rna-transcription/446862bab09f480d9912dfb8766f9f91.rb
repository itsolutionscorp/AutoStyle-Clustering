class Complement
	class << self
		def of_dna(dna)
			complements = {"G" =>"C", 'C'=>'G','T' =>'A','A' => 'U'}
			new_strand = ""
			dna.chars.each do |x|
				new_strand += complements[x]
			end
			new_strand
		end

		def of_rna(rna)
			complements = {"C" =>"G", 'G'=>'C','U' =>'A','A' => 'T'}
			new_strand = ""
			rna.chars.each do |x|
				new_strand += complements[x]
			end
			new_strand
		end
	end
end
