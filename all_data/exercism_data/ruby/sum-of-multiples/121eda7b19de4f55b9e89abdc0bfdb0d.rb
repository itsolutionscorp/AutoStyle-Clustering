require 'pry'
class SumOfMultiples
  def initialize(*args)
    @multiples_of = *args
  end

  def self.to(up_to)
    sum_to(1, up_to)
  end

  def self.sum_to(start, up_to)
    return 0 if up_to == 1
    (1...up_to).select { |num| num % 3 == 0 || num % 5 == 0 }.reduce(:+)
  end

  def check_multiple(num)
    @multiples_of.any? { |el| num % el == 0 }
  end

  def to(up_to)
    return 0 if up_to == 1
    (1...up_to).select { |num| check_multiple(num) }.reduce(:+)
  end

end

SumOfMultiples.to(1)
