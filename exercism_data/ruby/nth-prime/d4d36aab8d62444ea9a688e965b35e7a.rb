class Prime
  def self.is_prime(number)
    range = number - 1
    result = []
    (2..range).each do |x|
      if number % x == 0
        return false
        break
      end
    end
  end

  def self.nth(number)
    if number < 1
      raise ArgumentError.new
    else
      primes = []
      i=2
      until primes.length == number
        if is_prime(i) != false
          primes << i
        end
        i+=1
      end
      primes.last
    end
  end
end
