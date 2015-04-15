class Complement
	@transcribe = {G: :C, C: :G, T: :A, A: :U}

	class << self
	
		def of_dna(strand)
			rna = ''
			strand.split(//).each {|nucleotide| rna += @transcribe[nucleotide.to_sym].to_s}
			rna
		end

		def of_rna(strand)
			dna = ''
			strand.split(//).each {|nucleotide| dna += (@transcribe.invert)[nucleotide.to_sym].to_s}
			dna
		end
	end
end
