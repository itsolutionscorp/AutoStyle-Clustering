# nth Prime number exercism

class Prime

  def self.nth nth
    @prime_array = [2,3,5,7,11]
    @nth = nth
    if @nth < 1
      raise ArgumentError
    elsif @nth > 5
      primes
    else
      @prime_array[@nth-1]
    end
  end

  def self.primes
    i = @prime_array.length + 1
    current_prime = @prime_array[-1]

    while i <= @nth
      current_prime += 2
      divisor = 3
      # set boolean variable to true until proven false
      is_prime = true
      while divisor <= Math.sqrt(current_prime) && is_prime == true
        if current_prime % divisor == 0
          modulus = current_prime % divisor
          is_prime = false
        else
          divisor += 2
        end
      end

      if is_prime == true
        @prime_array << current_prime
        i += 1
      end
    end

    @prime_array[-1]
  end
end

puts Prime.nth(1000)
