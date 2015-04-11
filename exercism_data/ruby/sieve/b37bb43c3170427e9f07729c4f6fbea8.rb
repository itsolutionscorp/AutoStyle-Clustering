class Sieve
  def initialize n
    @n = n
  end

  def primes
    sieve = Array.new n + 1, true
    sieve[0] = false
    sieve[1] = false

    for i in 2..Math.sqrt(n)
      if sieve[i]
        (i * i).step n, i do |j|
          sieve[j] = false
        end
      end
    end

    sieve.map.each_with_index { |prime, index| index if prime }.compact
  end

  attr_reader :n
end
