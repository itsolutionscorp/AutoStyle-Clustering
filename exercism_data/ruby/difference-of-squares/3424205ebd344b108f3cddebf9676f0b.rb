class Squares
  attr_reader :sum_of_squares, :square_of_sums, :difference
  def initialize(num)
    @num = num
    @sum_of_squares = _operate(:sum, :square)
    @square_of_sums = _operate(:square, :sum)
    @difference = (sum_of_squares - square_of_sums).abs
  end
  def _operate(*operations_last_to_first)
    ints = Array(1..@num).extend(ArrayMath)
    operations_last_to_first.reverse.inject(ints,:send)
  end

  module ArrayMath
    def sum
      reduce(&:+)
    end

    def square
      map!(&:square)
    end
  end
end

class Integer
  def square
    self**2
  end
end
