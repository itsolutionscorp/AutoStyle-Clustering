class Sieve

  def initialize(max)
    @max = max
  end

  def primes
    range = []
    (2..@max).each { |num| range[num] = num }
    for i in 2..Math.sqrt(@max)
      if range[i]
        (i**2..@max).step(i) { |j| range[j] = nil }
      end
    end
    range.compact
  end

end
