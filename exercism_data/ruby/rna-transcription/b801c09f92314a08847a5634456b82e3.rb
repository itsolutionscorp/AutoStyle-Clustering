class Complement
	@@dnaToRna = {'G'=> 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	@@rnaToDna = {'G'=> 'C', 'C' => 'G', 'U'=> 'A', 'A' => 'T'}

	def self.of_dna (dna)
		transcription(dna,@@dnaToRna)
	end

	def self.of_rna (rna)
		transcription(rna, @@rnaToDna)
	end

	def self.transcription(str, hash)
		str.split('').inject('') {|result, char| result + hash[char]}
	end

end