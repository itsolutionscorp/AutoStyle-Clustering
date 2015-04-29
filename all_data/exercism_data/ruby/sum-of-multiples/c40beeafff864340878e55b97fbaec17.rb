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
    list.empty? ? 0 : @multiples.flat_map { |prime| multiples?(list, prime) }.uniq.inject(:+)
  end

  def multiples? list, prime
    list.select { |n| n % prime == 0 }
  end
end
