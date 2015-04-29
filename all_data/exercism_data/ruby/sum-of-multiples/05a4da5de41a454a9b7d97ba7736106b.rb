class SumOfMultiples
  
  def initialize(*args)
    @args = args
  end
  
  def self.to(num)
    (0...num).select {|x| x % 3 == 0 || x % 5 == 0}.inject(:+)
  end
  
  def to(num)
    (@args.min...num).select {|x| multiple?(x)}.inject(:+)
  end
  
  def multiple?(x)
    @args.any? {|y| x % y ==0}
  end
end
