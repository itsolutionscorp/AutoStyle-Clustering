class Grains
  def initialize
    @squares = 64
  end

  def square(int)
    # (1...int).inject(1){|sum, x| sum*x*2}
    2**(int-1)
  end

  def total
    2**(64)-1
  end
end
