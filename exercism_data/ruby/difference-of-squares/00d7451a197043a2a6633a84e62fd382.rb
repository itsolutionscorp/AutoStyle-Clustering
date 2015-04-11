class Array
  def sum # monkey-patch
    self.inject{ |sum,x| sum + x }
  end
end

class Squares
  attr_accessor :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sums**2
  end

  def sum_of_squares
    (squares).sum
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private
    def squares
      (1..@num).map do |x|
        x**2
      end
    end

    def sums
      (1..@num).to_a.sum
    end
end
