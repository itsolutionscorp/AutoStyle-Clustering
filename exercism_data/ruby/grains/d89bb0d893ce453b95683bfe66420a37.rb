class Grains
  def initialize
    @square_board = Array.new
    @sum_of_board = []
  end

  def square(num)
    i=1
    @num = num
    @square_board[1]=1
    while i<64
        @square_board[i+1] = 2*@square_board[i]
        i += 1
    end
    @square_board[@num]
  end

  #not sure how to use an instance method here to get an array that I can sum...
  def total
    @sum_of_board.square(64)
    sum=0
    @sum_of_board.each { |x| sum+=x }
  end
end
