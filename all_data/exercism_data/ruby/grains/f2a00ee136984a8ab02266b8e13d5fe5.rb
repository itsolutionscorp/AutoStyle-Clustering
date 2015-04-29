class Grains
  attr_reader :grains

  def initialize
    @grains = [1]
  end

  def square(amount)
    (amount-1).times {grains << grains.last*2} if amount > 1
    grains.last
  end

  def total(amount = 64)
    square(amount)
    grains.reduce(:+)
  end
end
