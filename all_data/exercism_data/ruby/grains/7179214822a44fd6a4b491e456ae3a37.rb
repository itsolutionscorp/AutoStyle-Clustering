class Grains
  def initialize
    @last_index = 0
  end
  
  def square(index = 1)
    raise "Exceeded Bounds" if index > 64
    2**(index-1)
  end
  
  def total
    @total ||= 1.upto(64).map {|s| square(s)}.inject(:+)
  end
end
