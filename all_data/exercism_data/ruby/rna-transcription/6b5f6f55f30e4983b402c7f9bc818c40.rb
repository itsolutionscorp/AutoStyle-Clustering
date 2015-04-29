class DNA
	def initialize(dna_string)
		@dna_string = dna_string
	end
	def count(nucleotide)
		@dna_string.split("").select do |n|
			n == nucleotide
		end.length
	end

	def nucleotide_counts
		cs = count("C")
		gs = count("G")
		ts = count("T")
		as = count("A")
		us = count("U")

		{"U" => us, "A" => as, "T" => ts, "G" => gs, "C" => cs, }
	end
end
