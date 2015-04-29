require 'active_support'
require 'active_support/core_ext'

class Squares
  def initialize(number)
    @side = number
    @range_array = (1..number).to_a
  end

  def square_of_sums
    (@range_array.sum)**2
  end

  def sum_of_squares
    @range_array.inject do |sum, number|
      sum + number**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
