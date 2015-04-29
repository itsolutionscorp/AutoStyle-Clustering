class Sieve

  def initialize(limit)
    @limit = limit
  end

  def primes
    (2..@limit).select do |number|
      (2..(number-1)).none? do |divisor|
        number % divisor == 0
      end
    end
  end

end
