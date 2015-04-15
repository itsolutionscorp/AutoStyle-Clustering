require 'debugger'

class Squares

	attr_accessor :number

	def initialize(num)
		@number = num
	end

	def sum_of_squares
		count = 1
		total = 0
		self.number.times do |num|
			total += count**2
			count += 1
		end
		total
	end


	def square_of_sums
		count = 1
		total = 0
		self.number.times do |num|
			total += count
			count += 1
		end
		total**2
	end

	def difference
		self.square_of_sums - self.sum_of_squares
	end

end
