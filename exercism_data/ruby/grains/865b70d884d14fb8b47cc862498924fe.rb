class Grains
  attr_reader :total, :board
  def initialize
    @board = [] 
    @total = 0
    create_board
  end

  def square(number)
    board[number-1]
  end

  private
    def create_board
      grains = 1
      64.times.each do 
        @board << grains
        @total += grains
        grains *= 2
      end
    end
end
