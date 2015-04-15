# RNA Transcription challenge

#  * `G` -> `C`
#  * `C` -> `G`
#  * `T` -> `A`
#  * `A` -> `U`


class Complement

	def self.of_dna(nucleotides)
		transcribe(nucleotides, { "G" => "C", "C" => "G", "T" => "A", "A" => "U"})
	end
	
	def self.of_rna(nucleotides)
		transcribe(nucleotides, { "G" => "C", "C" => "G", "U" => "A", "A" => "T"})
	end
	
	def self.transcribe(nucleotides, complements)
		# split the string of nucleotides into characters, and then convert that to an array
		# map the array so that each element is then looked up in the complements hash to find its match, which puts it into the result array
		# join the resulting array back together again into a non-seperated string
		transcription = nucleotides.chars.to_a.map {|n| complements[n]}.join("")
	end

end
