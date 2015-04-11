class Nucleotide
	private_class_method :new
	attr_reader :histogram

	def self.from_dna(dna)
		new(dna)
	end

	def initialize(dna)
		raise(ArgumentError, 'Invalid nucleotide found in dna!') if dna =~ /[^ATCG]/

		@dna = dna
		@histogram = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
		dna.each_char{|nucleotide| @histogram[nucleotide] += 1}
	end

	def count(nucleotide)
		@histogram[nucleotide]
	end
end

