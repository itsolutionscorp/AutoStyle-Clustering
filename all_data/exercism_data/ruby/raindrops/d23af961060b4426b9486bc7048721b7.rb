require 'prime'
require 'pry'

class Raindrops

  def self.convert(number)
    result = Prime.prime_division(number).inject("") do |string, result|
      string += "Pling" if result[0] == 3
      string += "Plang" if result[0] == 5
      string += "Plong" if result[0] == 7
      string  
    end
    result.empty? ? number.to_s : result
  end
end
