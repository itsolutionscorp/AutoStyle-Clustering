class Squares

def initialize (inputnumber)
	@inputnumber = inputnumber
end

def sum_of_squares
	output = 0
	(1..@inputnumber).each do |n| 
	output += n**2
	end
	return output
end

def square_of_sums
	output = 0
	sum = 0
	(1..@inputnumber).each do |n|
	sum += n 
	output = (sum)**2
	end
	return output
end

def difference
	output = 0
	output = square_of_sums - sum_of_squares
	return output
end

end
