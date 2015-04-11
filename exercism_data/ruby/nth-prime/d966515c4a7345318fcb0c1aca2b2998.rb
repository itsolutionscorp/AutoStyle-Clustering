class Prime

  def self.nth(number)
    if number <= 0
      raise ArgumentError, "Argument needs to be >= 1"
    end
    prime_array = Prime.first number
    prime_array.last
  end


  def self.first number_of_primes
    num = 2
    primes = []
    while (primes.count < number_of_primes)
      primes << num
      x = 2
      while (x <= num / 2)

        if (num % x == 0)
          primes.pop
          break
        end

        x += 1
      end
      num += 1
    end

    primes
  end
end
