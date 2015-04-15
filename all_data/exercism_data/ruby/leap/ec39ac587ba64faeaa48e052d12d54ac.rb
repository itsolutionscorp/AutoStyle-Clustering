class Year

def initialize(year)
	@year = year
end

def leap?
	return false if !even(4)
	return true if even(400)
	return !even(100)
end

private

def even(divisor)
	return @year.modulo(divisor).zero?
end
end
