class Grains
  def initialize
    @board = []
    set_board
  end

  def square num
    @board[num-1]
  end

  def total
    @board.reduce {|sum, num| sum + num}
  end

  private

  def set_board
    @board << 1
    (1..63).each do |i|
      @board << @board[-1] * 2
    end
  end
end
