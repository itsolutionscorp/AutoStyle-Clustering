class Sieve

  def initialize(limit)
    @limit = limit
  end

  def primes(range = Array(2..@limit), index = 0)
    if index >= (range.length / 2) + 1
      range
    else
      new_range = range.reject { |number| number % range[index] == 0 && number != range[index] }
      primes(new_range, index + 1)
    end
  end
end
