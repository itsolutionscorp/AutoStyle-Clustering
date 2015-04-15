class Grains

  def initialize(board_size = 64)
    @board_size = board_size
  end

  def square(n) # 2^(n-1)
    return 1 if n == 1
    Array.new(n-1){|i| 2 }.inject(&:*)
  end

  def total # Sum of powers of [1..n].each{|i| 2^i} = 2^(n+1) - 1
    square(@board_size + 1) - 1
  end
end
