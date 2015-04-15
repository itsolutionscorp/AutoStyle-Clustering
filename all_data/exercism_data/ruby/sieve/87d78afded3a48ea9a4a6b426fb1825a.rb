class Sieve < Struct.new(:max)
  def primes
    result = []
    is_prime = Array.new(max + 1, true)

    (2..max).each do |prime|
      next unless is_prime[prime]
      result << prime

      (2*prime..max).step(prime).each do |multiple|
        is_prime[multiple] = false
      end
    end

    result
  end
end
