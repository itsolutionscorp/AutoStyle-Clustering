class Prime
  def self.nth(number)
    raise ArgumentError if number < 1 || !number.is_a?(Fixnum) 

    prime_numbers = []
    number_being_checked = 2
    while prime_numbers.size < number
      prime_numbers << number_being_checked if number_is_prime?(number_being_checked)
      number_being_checked += 1
    end
    prime_numbers.last
  end

  def self.number_is_prime?(number)
    divisor = 2
    prime = true
    while number > divisor
      prime = false if number % divisor == 0
      break if prime == false
      divisor += 1
    end
    prime
  end
end
