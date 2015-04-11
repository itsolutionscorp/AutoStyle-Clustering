class Grains
  attr_reader :size
  def initialize(board_size = 64)
    @size = board_size
  end

  def square(square_number)
    (2 ** square_number)/2
  end

  def total
    size.times.inject(0) { |total, grains| total + square(grains+1) }
  end
end
