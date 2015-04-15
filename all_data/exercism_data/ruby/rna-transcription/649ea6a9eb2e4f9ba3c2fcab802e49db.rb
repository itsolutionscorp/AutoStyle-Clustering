class Complement

	@@dna_to_rna = {

		"G" => "C",
		"C" => "G",
		"T" => "A",
		"A" => "U"
	}

	@@rna_to_dna = @@dna_to_rna.invert

	def initialize(strand)
		@strand = strand
	end

	def self.of_dna (dna)
		new(dna).of_dna
	end

	def of_dna
		swap(strand, @@dna_to_rna)
	end

	def self.of_rna (rna)
		new(rna).of_rna	
	end

	def of_rna
		swap(strand, @@rna_to_dna)
	end

	private
	attr_reader :strand

	def swap(sequence, nucleotides)
		strandArr = sequence.split('')

		0.upto(strandArr.count) do |a|
			strandArr[a] = nucleotides[strandArr[a]]
		end
	  strandArr.join("")
	end
end
