class Grains

  attr_accessor :s
  def initialize
    @s = [0,1]
  end


  def square(n)
    s[n] ||= 2*square(n-1)
  end

  def total
    square(65)-1
  end

end
