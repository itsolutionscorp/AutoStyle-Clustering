module Prime

  def self.nth(pos)
    raise ArgumentError if pos < 1

    @cashed_primes ||= [2]
    
    return @cashed_primes[pos-1] unless @cashed_primes[pos-1].nil?
    
    item = @cashed_primes.last + 1

    while @cashed_primes.size < pos
      @cashed_primes << item if Prime.prime?(item) 
      item+=1
    end
    
    @cashed_primes[pos-1]

  end

  private 

    def self.prime?(item)
      
      return false if item % 2 == 0

      (2...Math.sqrt(item)+1).each do |div|
        if item % div == 0
          return false
        end
      end

      return true

    end
end
