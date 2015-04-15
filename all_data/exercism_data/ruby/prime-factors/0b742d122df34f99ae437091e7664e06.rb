class PrimeFactors
  def self.for(val)
    results = []
    primes = primes(val)

    num = val
    while num > 1
      primes.each do |prime|
        if num % prime == 0
          results << prime
          num = num / prime
        end
      end
    end
    results
  end

  def self.primes(max)
    results = (2..max).to_a
    (2...max).each { |i| (i+1..max).each { |j| results.delete(j) if j % i == 0 } }
    results
  end

end
