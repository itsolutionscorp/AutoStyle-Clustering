class SumOfMultiples

  def initialize(*args)
    @multiples = args
  end

  def self.to(num)
    new(3,5).to(num)
  end

  def to(num)
    (0..num-1).select { |i| multiple?(i) }.inject(:+)
  end

  def multiple?(num)
     @multiples.any? { |i| num % i == 0 }
  end
end 
