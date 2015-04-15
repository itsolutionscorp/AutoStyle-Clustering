class Complement

	def initialize(strand)
		@strand = strand
	end

	def self.of_dna (dna)
		new(dna).of_dna
	end

	def of_dna
		dnaHash = Hash.new
		dnaHash['G'] = 'C'	
		dnaHash['C'] = 'G'
		dnaHash['T'] = 'A'
		dnaHash['A'] = 'U'

		swap(strand, dnaHash)
	end

	def self.of_rna (rna)
		new(rna).of_rna	
	end

	def of_rna
		rnaHash = Hash.new
		rnaHash['C'] = 'G'	
		rnaHash['G'] = 'C'
		rnaHash['A'] = 'T'
		rnaHash['U'] = 'A'

		swap(strand, rnaHash)
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
