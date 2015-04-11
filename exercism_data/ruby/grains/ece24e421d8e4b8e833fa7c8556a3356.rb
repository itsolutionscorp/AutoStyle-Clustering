class Grains

  attr_reader :total

  def initialize
    @total = 2**NUMBER_OF_SQUARES - 1
  end

  def square(n)
    2**(n-1)
  end

  private
  NUMBER_OF_SQUARES = 64

end
