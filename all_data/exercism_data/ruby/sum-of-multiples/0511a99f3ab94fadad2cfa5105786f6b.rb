class SumOfMultiples

  def initialize(*factors)
    @factors = factors
  end

  def to(num)
    result = 0
    0.upto(num-1).select do |n|
      result += n if @factors.any? { |multiple| n % multiple == 0 }
    end
    result
  end

  def self.to(input, factors=[3, 5])
    new(*factors).to(input)
  end

end
