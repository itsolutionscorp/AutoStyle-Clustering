require 'prime'

class Raindrops
  @@raindrop_dictionary = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(input_number)
    prime_factors = calc_prime_factors(input_number.to_i)
    
    if prime_factors.nil? || (prime_factors & @@raindrop_dictionary.keys).empty?
      input_number.to_s
    else
      translate_to_raindrop_speak prime_factors
    end
  end

  def self.calc_prime_factors(integer)
    integer.prime_division.transpose.first
  end

  def self.translate_to_raindrop_speak(prime_factors)
    prime_factors.collect{ |i| @@raindrop_dictionary[i] }.join
  end
end
