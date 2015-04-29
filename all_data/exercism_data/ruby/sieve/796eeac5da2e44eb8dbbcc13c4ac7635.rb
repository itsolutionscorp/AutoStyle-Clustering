class Sieve

  def initialize(limit)
    @limit = limit
  end

  def primes(range = Array(2..@limit), index = 0)
    if index >= (range.length / 2) + 1
      range
    else
      remove_factors_of = range.reject { |number| number % range[index] == 0 && number != range[index] }
      primes(remove_factors_of, index + 1)
    end
  end
end
