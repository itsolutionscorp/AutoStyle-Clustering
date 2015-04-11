class Grains
  attr_reader :total

  def initialize
    @grains = []
    @grains[0] = 0
    @total = 0
    grains_on_square = 1
    64.times { |index|
      @grains[index + 1] = grains_on_square
      @total += grains_on_square
      grains_on_square *= 2
    }
  end

  def square(index)
    @grains[index]
  end

end
