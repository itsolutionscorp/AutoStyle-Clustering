class SumOfMultiples
  attr_reader :multiples

  def initialize(*multiples)
    @multiples = multiples.sort
  end

  def multiple?(number)
    multiples.any? { |multiple| number.modulo(multiple).zero? }
  end

  def to(limit)
    return 0 if limit <= multiples.first

    (1...limit).inject(0) do |sum, number|
      multiple?(number) ? sum += number : sum
    end
  end

  def self.to(limit)
    new(3, 5).to(limit)
  end
end
