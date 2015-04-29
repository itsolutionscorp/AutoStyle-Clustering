class Grains
  def initialize
    @squares = [0, 1]
    @rice = 0
  end

  def square(index)
    if @squares[index].nil?
      @squares[index] = 2 * self.square(index-1)
    else
      @squares[index]
    end
  end

  def total
    self.square(64)
    @squares.reduce(:+)
  end
end
