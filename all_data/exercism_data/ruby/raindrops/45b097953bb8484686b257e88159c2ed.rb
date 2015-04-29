require 'prime'

class Raindrops

  RAIN_HASH = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(rain_number)
    rain_factors = rain_number.prime_division.flatten.uniq.sort
                              .select { |factor| RAIN_HASH[factor] != nil }
                              .map    { |factor| RAIN_HASH[factor] }
    
    rain_factors.length > 0 ? rain_factors.reduce(:+) : rain_number.to_s
  end
end
