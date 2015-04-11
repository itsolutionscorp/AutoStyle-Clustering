class SumOfMultiples
  def initialize *args
    @factors = args
  end
  def self.to num 
    SumOfMultiples.new(3, 5).to(num)
  end
  def to num 
    (0...num).select {|x| 
      @factors.any? {|y| x % y == 0}
    }.reduce(:+)
  end
end
