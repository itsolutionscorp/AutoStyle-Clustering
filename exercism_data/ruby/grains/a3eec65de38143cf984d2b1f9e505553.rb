class Grains
  attr_reader :total

  def initialize
    @total = 2**64 - 1
  end

  def square(num)
    2**(num - 1)
  end

end
