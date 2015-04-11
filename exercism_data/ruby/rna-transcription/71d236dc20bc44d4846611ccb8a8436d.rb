module Complement

	def ~ 
		self.of_dna self
	end

	def -@
		self.of_rna self
	end

	extend self
	
	TO_RNA = {'G' => 'C', 'C'=> 'G', 'T' => 'A', 'A' => 'U'}
	TO_DNA = TO_RNA.invert
	
	def of_dna(strand)
		strand.gsub!(pattern(TO_RNA), TO_RNA)
	end

	def of_rna(strand)
		strand.gsub!(pattern(TO_DNA), TO_DNA)
	end
	
	private

	def pattern(transcription)
		pattern = Regexp.new('['+transcription.keys.join+']')
	end
end
