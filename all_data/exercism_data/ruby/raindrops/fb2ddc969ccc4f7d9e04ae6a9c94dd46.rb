require 'prime'

class Raindrops
  def self.convert(number)
    return "1" if number == 1
    Prime.prime_division(number).flatten.collect do |n|
      case 
      when n == 3 then "Pling"
      when n == 5 then "Plang"
      when n == 7 then "Plong"
      when n > 12 then return number.to_s
      else ""
      end
    end.join
  end
end
