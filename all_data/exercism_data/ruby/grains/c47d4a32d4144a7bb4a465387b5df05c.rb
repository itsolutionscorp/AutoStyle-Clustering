class Grains
  def initialize
  @correlation = 2
  @squares = 64
  end

  def square(num)
    @correlation ** (num - 1)
  end

  def total
    @correlation ** @squares - 1
  end

end
