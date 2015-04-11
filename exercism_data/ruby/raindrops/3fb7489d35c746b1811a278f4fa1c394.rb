class Raindrops
  MAPPING = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(number)
    prime_factors = self.prime_factor(number)
    matched_numbers = MAPPING.keys & prime_factors
    if matched_numbers.empty?
      number.to_s
    else
      matched_numbers.map{ |matched_number| MAPPING[matched_number] }.join
    end
  end

  private
    require 'prime'
    def self.prime_factor(number)
      primes = Prime.each
      prime = primes.next
      return [number] if number < prime
      prime_factors = []
      until prime > number
	quotient, remainder = number.divmod(prime)
	if remainder == 0
	  prime_factors << prime
	  number = quotient
	else
	  prime = primes.next
	end
      end
      prime_factors
    end
end
