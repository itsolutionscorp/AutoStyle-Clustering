class Palindromes
	Palindrome = Struct.new(:value, :factors)

	def initialize(max_factor:, min_factor: 1)
		@min, @max = min_factor, max_factor
	end

	def generate
		pairs.each do |i, j|
			palindromes[i*j] << [i, j] if palindrome_number?(i*j)
		end
	end

	def largest
		Palindrome.new(*palindromes.max)
	end

	def smallest
		Palindrome.new(*palindromes.min)
	end

	private

		def pairs
			[*@min..@max].repeated_combination(2).lazy
		end

		def palindrome_number?(n)
			n.to_s == n.to_s.reverse			
		end

		def palindromes
			@palindromes ||= Hash.new { |hash, key| hash[key] = [] }
		end
end
