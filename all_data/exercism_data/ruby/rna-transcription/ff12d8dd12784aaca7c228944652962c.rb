class Complement
	TRANSCRIBED = [['G', 'C'], ['C','G'], ['T', 'A'], ['A', 'U']]
	
	def initialize(nucleotides, strand)
		@nucleotides 	= nucleotides
		@strand 			= strand
	end

	def self.of_dna(strand)
		new('dna', strand).transcribed
	end

	def self.of_rna(strand)
		new('rna', strand).transcribed
	end

	def transcribed
		transcribed = if @nucleotides == 'dna'
			TRANSCRIBED.to_h
		else
			TRANSCRIBED.map(&:reverse).to_h
		end

		@strand.split("").map{ |s| transcribed.fetch(s) }.join
	end
end
