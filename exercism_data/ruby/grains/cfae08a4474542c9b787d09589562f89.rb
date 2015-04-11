class Grains
  def initialize
  end

  def square(nth)
  	2**(nth-1)
  end

  def total
  	square(65) - 1
  end
end
