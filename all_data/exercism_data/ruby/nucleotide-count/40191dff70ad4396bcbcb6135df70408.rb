class DNA

	def initialize(dna)
		validate_nucleotide(dna)
		@dna = dna	
	end
	
	def validate_nucleotide(dna)
		unless /^[GTCAgtca]+$/.match(dna) || dna == ''
		raise ArgumentError.new('No valid nucleotide sequence.')
		end
	end
	
	def char_count(string)		
		dna_hash = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
		arr = string.upcase.each_char.to_a
		arr.each do |char|
			dna_hash[char] += 1
		end
		dna_hash
	end
	
	def count(nucleotide)
		validate_nucleotide(nucleotide)
		hash = char_count(@dna)
		hash[nucleotide] || 0		
	end
	
	def nucleotide_counts
		hash = char_count(@dna)
	end

end
