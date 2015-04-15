require 'prime'

class Raindrops
  NUMBERS = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(number)
    string = ""
    prime_factors = Prime.prime_division(number).flatten.reject {|num| num == 1 }
    NUMBERS.each do |key, val|
      if prime_factors.include?(key)
        string << NUMBERS[key]
      end
    end

    if string.length < 1
      string = "#{number}"
    end
    string
  end
end
