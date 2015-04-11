class Squares

def initialize(num)
	@num = num
end

def square_of_sums
	total = 0
	[*1..@num].each do |n|
		total += n
	end
	return total**2
end

def sum_of_squares
	total = 0
	[*1..@num].each do |n|
		total += n**2
	end
	return total
end

def difference
	return square_of_sums - sum_of_squares
end

end
