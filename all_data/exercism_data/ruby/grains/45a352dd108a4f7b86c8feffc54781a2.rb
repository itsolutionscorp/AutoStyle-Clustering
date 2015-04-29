class Grains
  attr_accessor :total

  def initialize
    @total=0
    (1..64).each {|x|
      @total += square(x)}
  end

  def square(num)
    return 1 if num == 1
    2*square(num-1)
  end
end
