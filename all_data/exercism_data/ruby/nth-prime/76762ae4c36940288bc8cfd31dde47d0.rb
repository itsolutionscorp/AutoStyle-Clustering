module Prime

  def self.cashed_primes
    @cashed_primes ||= [2]
  end

  def self.nth(pos)
    raise ArgumentError if pos < 1
    cashed_primes
    return cashed_primes[pos-1] unless cashed_primes[pos-1].nil?
    num = cashed_primes.last + 1
    while cashed_primes.size < pos
      cashed_primes << num if Prime.prime?(num) 
      num+=1
    end
    cashed_primes[pos-1]
  end

  private 

  def self.prime?(num)
    prime = true
    (2...num/2+1).each do |div|
      if num % div == 0
        prime = false
        return
      end
    end
    prime
  end
end
