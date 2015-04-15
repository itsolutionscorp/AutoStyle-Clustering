class Sieve
  def initialize(limit)
    @limit = limit
    @range = (2 .. limit).to_a
  end

  def primes
    primes = [2]

    while @range.length > 0
      @range.select! { |n| (n % primes.last) != 0 }
      primes << @range.first unless @range.empty?
    end

    primes
  end
end
