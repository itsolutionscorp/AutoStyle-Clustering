# class Raindrops
require 'prime'

class Raindrops

  def self.convert number
    raindrop_map = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    raindrop_string = ""

    this_number_primes = Raindrops.prime_factors(number)
    matching_keys = this_number_primes & raindrop_map.keys

    return number.to_s if  matching_keys == []

    matching_keys.each do |matching_key|
      raindrop_string = raindrop_string + "#{raindrop_map[matching_key]}"
    end
    raindrop_string
  end

  def self.prime_factors number
    prime_factors_array = number.prime_division.map { |row| row[0] }
  end
end
