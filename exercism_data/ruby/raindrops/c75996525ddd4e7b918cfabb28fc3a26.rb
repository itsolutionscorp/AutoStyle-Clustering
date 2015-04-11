require 'prime'

class Raindrops
  VALUES = {  3 => 'Pling',
              5 => 'Plang',
              7 => 'Plong' }

  def self.convert(number)
    factors = number.prime_division.map { |prime, _| prime }
    result = ''
    VALUES.each do |factor, value|
      result += value if factors.include? factor 
    end

    result += number.to_s if result == ''
    return result
  end
end
