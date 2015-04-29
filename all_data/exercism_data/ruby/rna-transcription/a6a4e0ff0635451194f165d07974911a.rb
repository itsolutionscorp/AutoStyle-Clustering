class Complement

	def self.of_dna(input_strand)
		dna = ["G","C","T","A"]
		rna = ["C","G","A","U"]
		output_strand = ''

		input_strand.length.times do |i|
			output_strand << rna[dna.index(input_strand[i])]
		end

		output_strand

	end

	def self.of_rna(input_strand)
		dna = ["G","C","T","A"]
		rna = ["C","G","A","U"]
		output_strand = ''

		input_strand.length.times do |i|
			output_strand << dna[rna.index(input_strand[i])]
		end

		output_strand
	end


end
