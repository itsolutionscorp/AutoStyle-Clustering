class Complement

	@@complements = {"G"=>"C", "C"=>"G", "T"=>"A", "A"=>"U"}

	def self.of_dna(input_strand)
		output_strand = ''

		input_strand.each_char do |i|
			output_strand << @@complements[i]
		end

		output_strand

	end

	def self.of_rna(input_strand)
		output_strand = ''
		rev_complements = @@complements.invert

		input_strand.each_char do |i|
			output_strand << rev_complements[i]
		end

		output_strand
	end


end
