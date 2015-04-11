class PrimeFactors
  def self.for(val)
    results = []
    prime = 2

    while val >= prime
      if val % prime == 0
        results << prime
        val /= prime
      else
        prime += 1
      end
    end

    results
  end

end
