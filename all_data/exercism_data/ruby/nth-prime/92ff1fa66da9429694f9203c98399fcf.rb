module Prime

  def self.nth(size)
    raise ArgumentError unless size.is_a?(Fixnum) && size > 0 
    return @cashed_primes[size-1] unless @cashed_primes.nil? || @cashed_primes[size-1].nil?
    
    @cashed_primes ||= [2]
    candidate_prime = @cashed_primes.last + 1

    while @cashed_primes.size < size
      @cashed_primes << candidate_prime if prime?(candidate_prime) 
      candidate_prime += 1
    end
    
    @cashed_primes[size-1]
  end

  def self.prime?(candidate)
    return false if candidate % 2 == 0

    (2...Math.sqrt(candidate)+1).each do |div|
      if candidate % div == 0
        return false
      end
    end

    return true
  end

  private_class_method :prime?
end
