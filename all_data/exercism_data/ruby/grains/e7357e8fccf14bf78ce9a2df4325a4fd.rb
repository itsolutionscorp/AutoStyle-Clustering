class Grains
  attr_accessor :grains
  
  def initialize
    @grains = []
  end

  def square(nr)
    v = if nr == 1
          1
        else
          nr = 2 * square(nr-1)
        end
    grains << v
    v
  end

  def total
    square(64)
    grains.reduce(:+)
  end
end
