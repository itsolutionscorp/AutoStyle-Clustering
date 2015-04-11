class SumOfMultiples
  def self.to(limit)
    new(3, 5).to(limit)
  end

  def initialize(*multiples)
    @multiples = multiples
  end

  def to(limit)
    array = [0]
    (1...limit).select do |n|
      array << n if multiples?(n)
    end
    array.inject(:+)
  end

  private

  def multiples?(n)
    @multiples.any? { |multiple| n % multiple == 0 }
  end
end
