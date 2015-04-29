class Triplet

	class << self
		def where(criteria)
			sum = criteria[:sum]
			min = criteria[:min_factor] || 1
			max = criteria[:max_factor]
			
			list = [*min..max].combination(3).map { |nums| new(*nums) }
			list = list.select { |t| t.sum == sum } unless sum.nil?
			list = list.select(&:pythagorean?)
		end
	end
	
	def initialize(a, b, c)
		@nums = [a, b, c]
	end
	
	def sum
		@nums.reduce(:+)
	end
	
	def product
		@nums.reduce(:*)
	end
	
	def pythagorean?
		first_two_numbers_squared == last_number_squared
	end

private

	def first_two_numbers_squared
		@nums.take(2).inject(0) { |sum, num| sum + (num ** 2) }
	end
	
	def last_number_squared
		(@nums.last ** 2)
	end
end
