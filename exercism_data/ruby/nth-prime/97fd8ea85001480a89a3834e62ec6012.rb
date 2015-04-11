module Prime

  def self.nth(size)
    raise ArgumentError if size < 1
    return @cashed_primes[size-1] unless @cashed_primes.nil? || @cashed_primes[size-1].nil?
    
    @cashed_primes ||= [2]
    candidate_prime = @cashed_primes.last + 1

    while @cashed_primes.size < size
      @cashed_primes << candidate_prime if Prime.prime?(candidate_prime) 
      candidate_prime += 1
    end
    
    @cashed_primes[size-1]
  end

  private 

    def self.prime?(candidate)
      return false if candidate % 2 == 0

      (2...Math.sqrt(candidate)+1).each do |div|
        if candidate % div == 0
          return false
        end
      end

      return true
    end
end
