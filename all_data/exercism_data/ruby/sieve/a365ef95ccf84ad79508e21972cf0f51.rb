class Sieve
  def initialize(n)
    @limit = n
  end

  def primes
    numbers = [2, 3] + (5...@limit).step(2).to_a
    numbers.each do |p|
      n = 2 * p
      while n < @limit
        numbers.delete(n)
        n += p
      end
    end
    numbers
  end
end
