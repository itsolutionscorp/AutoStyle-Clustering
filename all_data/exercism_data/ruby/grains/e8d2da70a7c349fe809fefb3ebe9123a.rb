# This version is very slightly optimized but follows the letter of the method (doubling each in turn, and only when requested)

class Grains
  def initialize
    @squares = []
    @squares[1] = 1
    @total = nil
  end

  def square(x)
    if @squares[x].nil?
      @squares[x] = square(x-1) * 2
    end
    return @squares[x]
  end

  def total
    if @total.nil?
      @total = (1..64).inject(0){ | result, element | result += square(element) }
    end
    return @total
  end
end
