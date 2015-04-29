class SumOfMultiples

  def initialize(*multiples)
    @multiples = multiples
  end

  def self.to(number)
    new(3, 5).to(number)
  end

  def to(end_number)
    (3..end_number s- 1).reduce(0) do |sum, number|
      is_multiple?(number) ? sum + number : sum
    end
  end

  def is_multiple?(number)
    @multiples.any? { |multiple| number % multiple == 0 }
  end

end

SumOfMultiples.to(4)
