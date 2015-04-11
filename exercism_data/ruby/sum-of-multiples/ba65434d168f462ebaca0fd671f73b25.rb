class SumOfMultiples
  MULTIPLES = [3, 5]

  def initialize *args
    @multiples = args.empty? ? MULTIPLES : args
  end

  def self.to number
    new.to(number)
  end

  def to number
    list = [*1...number]
    @multiples.flat_map { |prime| multiples?(list, prime) }.uniq.inject(:+) || 0
  end

  private

  def multiples? list, prime
    list.select { |n| n % prime == 0 }
  end
end
