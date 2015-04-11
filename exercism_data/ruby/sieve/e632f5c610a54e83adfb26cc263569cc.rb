require 'prime'

class Sieve
  def initialize(number)
    @number = number
  end

  def primes
    counter = 1
    original_array = []
    prime_array = []
    new_array = []

    original_array << @number
    while counter < @number
      original_array.each do |number|
        new_array << (number - counter)
        counter += 1
      end
    end
    
    new_array.each do |number|
      if number.prime?
        prime_array << number
      end
    end
    prime_array.reverse
    
  end

end
