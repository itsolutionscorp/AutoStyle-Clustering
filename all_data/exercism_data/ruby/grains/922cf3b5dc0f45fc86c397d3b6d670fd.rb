class Grains
  def initialize
    @total_grains
  end
  
  def square(n)
    2 ** (n-1)
  end
  
  def total
    @total_grains ||= compute_total
  end
  
  private
  
  def compute_total
    (1..64).collect {|n| square(n)}.inject(:+)
  end
end
