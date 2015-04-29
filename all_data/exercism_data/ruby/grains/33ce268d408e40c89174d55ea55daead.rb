class Grains
  def initialize
  end

  def square(nth)
  	2**(nth-1)
  end

  def total
  	total = 1
  	(1..63).to_a.map { |i| total += 2**i }
    total
  end
end
