class SumOfMultiples
  def initialize(*divisors)
    @divisors = divisors
  end

  def to(num)
    (1..num-1).reduce(0) do |res, n|
      @divisors.reduce(nil) { |mul, div| mul |= n % div == 0 } ? res + n : res
    end
  end

  def self.to(num)
    new(3, 5).to(num)
  end
end
