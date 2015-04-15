class Grains

  #set a chess board with 64 numbered squares
  #chessboard_squares = []
  #chessboard_squares = [1-64]; shovel into array 64 squares

  def initialize
    @grains = []
    @chessboard = (1..64)
  end

  def square(x)
      number_grains = 2**(x-1)
      @grains << number_grains
      return number_grains
  end

  def total
    sum = 0
    @chessboard.each do |x|
      sum += self.square(x)
    end
    return sum
  end


end



#Write a program that calculates the number of grains of wheat on a chessboard given that the number on each square doubles.

#There once was a wise servant who saved the life of a prince. The king promised to pay whatever the servant could dream up. Knowing that the king loved chess, the servant told the king he would like to have grains of wheat. One grain on the first square of a chess board. Two grains on the next. Four on the third, and so on.

#There are 64 squares on a chessboard.

#Write a program that shows
#- how many grains were on each square, and
#- the total number of grains
