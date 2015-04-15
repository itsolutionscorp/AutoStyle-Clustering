class Grains
  def initialize
    @square_counts = []
  end

  def square square_num
    @square_counts[square_num] ||= calculate_square_count_for square_num
  end

  def calculate_square_count_for square_num
    2 ** (square_num - 1)
  end

  def total
    @total ||= calculate_total
  end

  def calculate_total
    (1..64).reduce { |total, square_num| total += square square_num }
  end
end
