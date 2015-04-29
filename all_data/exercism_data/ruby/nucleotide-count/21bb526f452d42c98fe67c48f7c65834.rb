class Nucleotide

	def initialize(dna)
		@dna = dna
	end

	def self.from_dna(dna)
		raise ArgumentError, 'Invalid DNA.' if dna =~ /[^ATCG]/
		new(dna)
	end

	def count(letter)
		sum = 0
		@dna.each_char do |chr|
			sum += 1 if chr == letter
		end
		sum
	end

	def histogram
		hist = Hash.new
		hist['A'] = count('A')
		hist['T'] = count('T')
		hist['C'] = count('C')
		hist['G'] = count('G')
		hist
	end

end
