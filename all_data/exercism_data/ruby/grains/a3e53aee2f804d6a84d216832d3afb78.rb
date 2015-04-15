class Numeric

  def raise_to(n)
    self ** n
  end

  def lessen(n=1)
    self - n
  end

end

class Grains

  attr_reader :total

  def initialize
    @total = 2.raise_to(64).lessen
  end
  
  def square(n)
    unless n.between?(1, 64) && n.integer?
      raise ArgumentError, "Square must be a whole number between 1 and 64"
    end

    2.raise_to(n.lessen)
  end

end
