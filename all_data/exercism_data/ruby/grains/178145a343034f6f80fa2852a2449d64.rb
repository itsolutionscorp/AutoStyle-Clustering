# Strategy 1: baking all values
class Grains
  attr_reader :total

  def initialize
    @squares = generate_squares
    @total   = @squares.reduce(:+)
  end

  def square(i)
    @squares[i-1]
  end

  private

  def generate_squares
    [1].tap do |squares|
      63.times { squares.push 2*squares.last }
    end
  end
end
