# Thinking like a software engineer:
# This version is readable and maintainable, it makes it
# clear what operations are going on and how they work:
class Squares
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    (1..number).collect { |i| i**2 }.reduce(&:+)
  end

  def square_of_sums
    (1..number).reduce(&:+)**2
  end

  def difference
    square_of_sums-sum_of_squares
  end
end

# Thinking like a mathematicican:
# This version makes use of mathematical formulas and
# properties.  It's harder to understand if the math
# is new or unfamiliar but is WAY faster.
class Squares
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    (number*(number+1)*(2*number+1))/6
  end

  def square_of_sums
    (number*(1+number)/2)**2
  end

  def difference
    square_of_sums-sum_of_squares
  end
end
