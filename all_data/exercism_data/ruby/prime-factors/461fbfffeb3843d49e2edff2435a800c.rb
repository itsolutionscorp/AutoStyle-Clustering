module PrimeFactors
  def self.for nr
    res = []
    possible_primes_below(nr) do |divisor|
      while nr >= divisor && nr % divisor == 0
        res << divisor
        nr /= divisor
      end
      break if nr == 1
    end
    res
  end

  def self.possible_primes_below nr
    yield 2
    yield 3
    yield 5
    2.upto(nr/6 + 1) do |x|
      yield 6*x + 1
      yield 6*x + 5
    end
  end
end
