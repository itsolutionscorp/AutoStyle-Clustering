class Palindromes
	attr_reader :largest, :smallest

	def initialize(max_factor:, min_factor: 1)
		@max_factor = max_factor
		@min_factor = min_factor
	end

	def generate()
		palindromes = Hash.new{|hash, palindrome| hash[palindrome] = Palindrome.new(palindrome)}
		(@min_factor..@max_factor).each do |i|
			(i..@max_factor).each do |j|
				palindromes[i*j].factors << [i, j] if palindrome?(i*j)
			end
		end

		@largest = palindromes[palindromes.keys.max]
		@smallest = palindromes[palindromes.keys.min]
	end

	private 

	def palindrome?(num)
		num = num.to_s
		num == num.reverse
	end
end

class Palindrome
	attr_reader :value, :factors

	def initialize(value)
		@value = value
		@factors = []
	end
end
