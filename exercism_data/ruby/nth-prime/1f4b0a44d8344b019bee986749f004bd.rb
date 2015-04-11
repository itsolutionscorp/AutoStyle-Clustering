class Prime
  @@primes = [2]

  def self.nth(index)
    raise ArgumentError if index < 1
    while @@primes.length < index
      @@primes << find_next(@@primes.last)
    end
    return @@primes[index - 1]
  end

  def self.find_next(number)
    number+=1
    until prime?(number) do
      number+=1
    end
    return number
  end

  def self.prime?(number)
    (2..number-1).each do |div|
      return false if number % div == 0
    end
    return true
  end
end
