class Grains

  attr_reader :total, :board_size

  def initialize(options = {})
    @board_size = options[board_size] || 64
    @total ||= calculate_total
  end
  
  def square(num)
    (1..num).inject { |sum| sum * 2 }
  end

  def calculate_total
    square(board_size) * 2 - 1
  end

end
