class Sieve
  attr_reader :limit

  def initialize(limit)
    @limit = limit
  end

  def primes
    return [] if limit < 2

    sieve!

    [].tap do |primes|
      candidates.each_with_index do |prime, candidate|
        primes << candidate if prime
      end
    end
  end

  private

  def sieve!
    base_numbers.each do |n|
      strike_multiples_of(n) if candidate?(n)
    end
  end

  def candidates
    @candidates ||= [false, false] + Array.new(@limit - 2, true)
  end

  def candidate?(n)
    candidates[n]
  end

  def base_numbers
    2..Math.sqrt(limit)
  end

  def strike_multiples_of(n)
    multiple = n*n
    while multiple <= limit
      skip!(multiple)
      multiple += n
    end
  end

  def skip!(n)
    candidates[n] = false
  end
end
