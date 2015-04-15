class Squares
  def initialize(num)
    @num = num                                 # => 5, 5, 5
    @square_of_sums = 0                        # => 0, 0, 0
    @sum_of_squares = 0                        # => 0, 0, 0
  end
  def square_of_sums
    1.upto(@num) do |n|                        # => 1, 1
      @square_of_sums += n                     # => 1, 3, 6, 10, 15, 1, 3, 6, 10, 15
    end                                        # => 1, 1
    @square_of_sums**2                         # => 225, 225
  end
  def sum_of_squares
    1.upto(@num) do |n|                        # => 1, 1
      @sum_of_squares += (n**2)                # => 1, 5, 14, 30, 55, 1, 5, 14, 30, 55
    end                                        # => 1, 1
    @sum_of_squares                            # => 55, 55
  end
  def difference
    self.square_of_sums - self.sum_of_squares  # => 170
  end
end
