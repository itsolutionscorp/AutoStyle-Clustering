class Sieve

  def initialize(max)
    @max = max
  end

  def primes
    results = (2..@max).to_a
    (2...@max).each { |i| (i+1..@max).each { |j| results.delete(j) if j % i == 0 } }
    results
  end

end
