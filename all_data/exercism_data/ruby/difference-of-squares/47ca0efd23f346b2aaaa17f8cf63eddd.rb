class Delegate
  @@cache = Hash.new

  def initialize(number)
    @factor = ((number << 1) + 1)
    @temp = number*(number + 1) >> 1
  end

  def square_of_sums
    @square_of_sums ||= @temp * @temp
  end

  def sum_of_squares
    @sum_of_squares ||= @temp * @factor / 3
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def self.factory(number)
    @@cache[number] ||= Delegate.new(number)
  end
end

class Squares
  def self.new(number)
    Delegate.factory(number)
  end
end
