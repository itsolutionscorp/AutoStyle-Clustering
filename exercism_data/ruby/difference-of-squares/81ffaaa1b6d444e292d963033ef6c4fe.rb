#accept a number, which is what the range goes up to
#three functions -- square_of_sums, sum_of_squares, difference: each resulting in an integer

class Squares

  def initialize(n)
    @end_of_range = n
  end

  def square_of_sums
    #(1 + 2 + ... + 10)**2 = 55**2 = 3025
    array_from_range = (1..@end_of_range).to_a
    # array_from_range.inject{|sum,x| sum + x }
    (array_from_range.inject(:+))**2
  end

  def sum_of_squares
    squares_array = Array.new
    # 1**2 + 2**2 + ... + 10**2 = 385
    (1..@end_of_range).each do |a_number|
      squares_array << (a_number ** 2)
    end
    squares_array.inject(:+)
  end

  def difference
    # 3025 - 385 = 2640
    square_of_sums - sum_of_squares
  end
end
