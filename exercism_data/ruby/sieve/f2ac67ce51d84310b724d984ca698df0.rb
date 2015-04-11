class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    numbers = [*2..@limit]
    result = []

    while current = numbers[0]
      result << current
      numbers.reject! { |n| n % current == 0 }
    end
    result
  end
end
