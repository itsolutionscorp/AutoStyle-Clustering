class SumOfMultiples
  def initialize(*divisors)
    @divisors = *divisors
  end

  def self.to(num)
    SumOfMultiples.new(3,5).to(num)
  end

  def to(num)
    return 0 if num < @divisors.min
    ans = []
    (1...num).map do |x|
      @divisors.each {|y| ans << x && break if x % y == 0}
    end
    ans.inject(:+)
  end
end
