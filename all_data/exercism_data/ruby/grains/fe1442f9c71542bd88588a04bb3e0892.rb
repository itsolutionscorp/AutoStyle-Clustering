class Grains

  def initialize
  end

  def square(sq)
    sq > 1 ? previous_square(sq) * 2 : 1
  end

  def total
    all_values.reduce(:+)
  end

  private

  def previous_square(sq)
    square sq - 1
  end

  def all_values
    1.upto(64).map { |sq| square(sq) }
  end

end
