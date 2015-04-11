class Grains

  def initialize
  end

  def square(sq)
    sq > 1 ? previous_square(sq) * 2 : 1
  end

  def previous_square(sq)
    square sq - 1
  end

  def total
    1.upto(64).map { |sq| square(sq) }.reduce(:+)
  end

end
