module Complement

	def ~ 
		self.of_dna self
	end

	def -@
		self.of_rna self
	end

	extend self
	
	TRANSCRIBE = {G: :C, C: :G, T: :A, A: :U}
	
	def of_dna(strand)
		rna = ''
		strand.size.times do |nucleotide|
			rna += TRANSCRIBE[strand[nucleotide].to_sym].to_s
		end
		rna
	end

	def of_rna(strand)
		dna = ''
		strand.size.times do |nucleotide| 
			dna += (TRANSCRIBE.invert)[strand[nucleotide].to_sym].to_s
		end
		dna
	end
end
