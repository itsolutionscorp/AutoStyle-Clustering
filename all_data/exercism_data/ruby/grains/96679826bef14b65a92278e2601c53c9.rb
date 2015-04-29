class Grains

  attr_reader :total

  def initialize
    @total = 2 ** 64 - 1
  end
  
  def square(n)
    2 ** (n - 1)
  end

end
