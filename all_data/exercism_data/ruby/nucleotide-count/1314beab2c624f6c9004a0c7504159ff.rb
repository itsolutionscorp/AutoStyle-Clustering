class DNA < String
	def initialize dna
		if dna =~ /[^ACGT]/ and not dna.empty?
			raise ArgumentError, "This is an invalid dna string"
		end
		@s = dna.to_s
		super @s
	end

	def count dna
		if dna =~ /[^ACGTU]/  and not dna.empty?
			raise ArgumentError, "This is an invalid dna string"
		end

		super
	end

		
	
	def nucleotide_counts
		nucleotide_count = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
		@s.chars.sort.each{ | nucleotide | nucleotide_count[nucleotide] += 1 }
		return nucleotide_count
	end


end
