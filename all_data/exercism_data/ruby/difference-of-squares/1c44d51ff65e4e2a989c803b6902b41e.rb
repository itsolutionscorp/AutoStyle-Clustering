class Squares
	attr_reader :number

	def initialize(number)
		@number = number
	end

	def square_of_sums
		sum = 0
		self.natural_numbers[0..number].each do |natural_number|
			sum += natural_number
		end
		sum ** 2
	end

	def sum_of_squares
		sum = 0
		self.natural_numbers[0..number].each do |natural_number|
			sum += natural_number ** 2
		end
		sum
	end

	def difference
		self.square_of_sums - self.sum_of_squares
	end

	def natural_numbers
		(0..100).to_a
	end


end
