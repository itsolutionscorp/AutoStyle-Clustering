require "ostruct"

class Palindromes
	attr_accessor :values, :factors, :range

	def initialize(max_factor:, min_factor: 1)
		@range = min_factor..max_factor
		@values = []
		@factors = []
	end

	def generate
		[*range].repeated_permutation(2).each do |i, j|
			if palindrome_number?(i*j)
				add_factors(i, j)
				@values << i*j
			end
		end
	end

	def largest
		value = values.sort[-1]
		OpenStruct.new(value: value, factors: factors[value])
	end

	def smallest
		value = values.sort[0]
		OpenStruct.new(value: value, factors: factors[value])
	end

	private

		def palindrome_number?(n)
			n.to_s == n.to_s.reverse			
		end

		def add_factors(i, j)
			factors[i*j] ||= []
			factor = i <= j ? [i,j] : [j,i]
			factors[i*j] << factor unless factors[i*j].include? factor
		end
end
