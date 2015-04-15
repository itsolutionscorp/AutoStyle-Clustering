class SumOfMultiples
  def initialize(*multiples)
    @multiples = multiples
  end

  def self.to(limit)
    self.new(3, 5).to(limit)
  end

  def to(limit)
    numbers = (1...limit)
    total = 0
    numbers.each do |number|
      total += number if multiple?(number)
    end
    total
  end

  def multiple?(number)
    @multiples.map { |multiple| number % multiple }.include?(0)
  end
end
