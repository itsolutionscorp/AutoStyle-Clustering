class SumOfMultiples
  def self.to num
    new(3,5).to(num)
  end

  def to num
    (1..num-1).entries.select do |entry| 
      @divisors.any? { |divisor| entry % divisor == 0}
    end.reduce(:+) || 0
  end

  def initialize *params
    @divisors = params
  end
end
