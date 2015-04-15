class Nucleotide

	def initialize(input)
		raise ArgumentError if input =~ /[^ATCG]/
		@input = input.split('')
	end

	def count (nucleotide)
		@input.inject(0) {|count, letter| count += 1 if letter == nucleotide; count}
	end

	def self.from_dna(letters)
		new(letters)
	end

	def histogram
		['A', 'T', 'C', 'G'].each_with_object({}) do |letter, histogram|
			histogram[letter] = @input.count("#{letter}")
		end
	end

end
