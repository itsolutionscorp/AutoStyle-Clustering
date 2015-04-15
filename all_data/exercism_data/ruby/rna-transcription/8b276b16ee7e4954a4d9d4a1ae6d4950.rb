class Complement
	Transcription = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	RNA_to_DNA = Transcription.invert
	
	def self.of_dna(strand)
		return complement(strand, Transcription);
	end
	
	def self.of_rna(strand)
		dna = ""
		strand.length.times{ |i|
			dna += RNA_to_DNA[strand[i]];
		}
		return dna		
	end
	
	def self.complement(strand, key)
		result = ""
		strand.chars.map{|character| result+=key[character]}
		return result
	end
end
