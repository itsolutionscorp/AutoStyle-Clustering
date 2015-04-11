class SumOfMultiples
  def initialize(*base_numbers)
    @base_numbers = base_numbers
  end

  def self.to(limit)
    new(3, 5).to(limit)
  end

  def to(limit)
    0.upto(limit - 1).select { |i| multiple? i }.inject(:+)
  end

private
  def multiple?(number)
    @base_numbers.find { |base| number % base == 0 }
  end
end
