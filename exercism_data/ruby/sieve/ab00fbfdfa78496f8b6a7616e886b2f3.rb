class Sieve
  attr_reader :value

  def initialize value
    @value = value
  end

  def primes
    (1..value).select{ |n| prime?(n) }
  end

  def prime? n
    (1..n).count { |i| n % i == 0 } == 2
  end
end
