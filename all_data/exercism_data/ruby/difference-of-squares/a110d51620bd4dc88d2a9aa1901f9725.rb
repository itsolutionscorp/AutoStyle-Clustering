# Difference Of Squares

# Find the difference between the sum of the squares and the square of the sums of the first N natural numbers.

# The sum of the squares of the first ten natural numbers is,

#     1**2 + 2**2 + ... + 10**2 = 385

# The square of the sum of the first ten natural numbers is,

#     (1 + 2 + ... + 10)**2 = 55**2 = 3025

# Hence the difference between the sum of the squares of the first ten
# natural numbers and the square of the sum is 3025 - 385 = 2640.

class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    @num.downto(1).reduce(:+)**2
  end

  def sum_of_squares
    @num.downto(1).reduce(0) { |sum, x| sum + x**2 }
  end

  def difference
     square_of_sums - sum_of_squares 
  end
end
