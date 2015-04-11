class PrimeFactors

  def self.for(number)
    primes = []
    return primes if number <= 1

    current_divisor = 2

    loop do
      quotient, modulus = number.divmod(current_divisor)

      if modulus == 0
        number = quotient
        primes << current_divisor
        current_divisor = 1

        break if quotient == 1
      end
      current_divisor += 1

    end

    primes
  end

end
