require "prime"

class PrimeFactors
  def self.for(number)
    factors = []
    dividend = number

    loop do
      return factors if dividend == 1

      Prime.instance.each do |prime_divisor|
        quotient, modulus = dividend.divmod(prime_divisor)

        if modulus.zero?
          factors << prime_divisor
          dividend = quotient
          break
        end
      end
    end
  end
end
