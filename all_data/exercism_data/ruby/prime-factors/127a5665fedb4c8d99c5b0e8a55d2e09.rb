module PrimeFactors
  def self.for n
    primes = []

    (2..n).each do |candidate|
      while n % candidate == 0
        primes << candidate
        n /= candidate
      end
    end

    primes
  end
end
