class Grains

  attr_reader :square_number

  def initialize square_number = 64
    @square_number = square_number
  end

  def square n
    raise ArgumentError unless (1..square_number).include? n
    2 ** (n - 1)
  end

  def total
    (1..square_number).map { |n| square(n) }.inject(:+)
  end

end
