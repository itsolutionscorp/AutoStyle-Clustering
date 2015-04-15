class Grains

  attr_reader :total

  def initialize
    @total = (2 ** 64).pred
  end

  def square(n)
    unless n.between?(1, 64) && n.integer?
      raise ArgumentError, "Square must be a whole number between 1 and 64"
    end

    2 ** n.pred
  end

end
