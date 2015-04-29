class Grains

  def initialize board_size: 64
    @board_size = board_size
  end

  def square square_number
    2 << square_number - 2
  end

  def total
    (2 << @board_size - 1) - 1
  end
end
