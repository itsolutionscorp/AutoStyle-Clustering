class Grains
  NUM_OF_SQUARES = 64
  attr_reader :squares

  def initialize
    @squares = []
    NUM_OF_SQUARES.times do |i|
      @squares[i] = 2**(i)
    end
  end

  def square(num)
    squares[num-1]
  end

  def total
    squares.inject(&:+)
  end

end
