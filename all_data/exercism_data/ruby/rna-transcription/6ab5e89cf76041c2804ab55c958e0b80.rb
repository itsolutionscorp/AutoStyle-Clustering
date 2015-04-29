class Complement
	Transcription = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	
	def self.of_dna(strand)
		return complement(strand, Transcription)
	end
	
	def self.of_rna(strand)
		return complement(strand, Transcription.invert)	
	end
	
	def self.complement(strand, key)
		return strand.chars.map{|character|key[character]}.join
	end
end
