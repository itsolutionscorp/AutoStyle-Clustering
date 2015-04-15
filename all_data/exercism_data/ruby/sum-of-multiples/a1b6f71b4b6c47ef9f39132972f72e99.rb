# class to sum multiples
class SumOfMultiples
  def initialize(*args)
    @multiples = args
  end

  def self.to(limit, multiples = [3,5])
    (0...limit).reduce(0) { |total, i| total += is_multiple?(i, multiples) }
  end

  def self.is_multiple?(n, multiples)
    multiples.any? { |m| n % m == 0 } ? n : 0
  end

  def to(limit)
    self.class.to(limit, @multiples)
  end
end
