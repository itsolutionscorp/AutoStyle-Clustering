class Sieve
  attr_reader :num

  def initialize(num)
    @num = num
  end

  def prime?(n)
    (2...n).none? { |x| n % x == 0 }
  end

  def primes
    (2..num).select { |n| prime?(n) }
  end
end
