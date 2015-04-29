class SumOfMultiples

  def initialize(*args)
    @multiples = args
  end

  def self.to(num)
    SumOfMultiples.new(3,5).to(num)
  end

  def to(num)
    (1..num-1).inject(0) { |sum,n| a_multiple?(n) ? sum + n : sum }
  end

  def a_multiple?(num)
     @multiples.any? { |i| num % i == 0 }
  end
end 
