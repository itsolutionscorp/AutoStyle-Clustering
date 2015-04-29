class SumOfMultiples
  def initialize(*args)
    @multiples_of = *args
  end

  def self.to(up_to)
    return 0 if up_to == 1
    (1...up_to).select { |num| num % 3 == 0 || num % 5 == 0 }.reduce(:+)
  end

  def to(up_to)
    return 0 if up_to == 1
    (1...up_to).select { |num| check_multiple(num) }.reduce(:+)
  end

  private

  def check_multiple(num)
    @multiples_of.any? { |el| num % el == 0 }
  end
end
