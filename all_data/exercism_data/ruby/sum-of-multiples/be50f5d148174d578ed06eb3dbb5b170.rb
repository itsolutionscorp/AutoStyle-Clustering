class SumOfMultiples
  def self.to(number)
    new(5, 3).to(number)
  end

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(number)
    sum = 0
    (1...number).each do |i|
      sum += i if @multiples.any? { |multiple| i % multiple == 0 }
    end
    sum
  end

end
