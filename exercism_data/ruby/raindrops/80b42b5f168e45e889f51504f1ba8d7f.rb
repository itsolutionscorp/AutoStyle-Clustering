require 'prime'

class Raindrops
  ALLOWED_NUMBERS = [3,5,7]
  DROP_NUMBER_TO_STRING = { :"#{3}" => 'Pling', :"#{5}" => 'Plang', :"#{7}" => 'Plong' }

  def self.convert(number)
    raindrops = number.prime_division.reduce("") do |string, (prime, _)|
      if ALLOWED_NUMBERS.include?(prime)
        string += DROP_NUMBER_TO_STRING[prime.to_s.to_sym]
      else
        string
      end
    end

    raindrops.empty? ? number.to_s : raindrops
  end
end
