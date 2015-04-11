# Raindrops:
# Write a program that converts a number to a string, the contents of which depends on the number's prime factors.

class Raindrops
  def self.convert number
    output=''
    factors.keys.each do |key|
      if number % key == 0
        output << factors[key]
      end
    end
    if output == ''
      output << number.to_s
    end
    output
  end

  def self.factors
    {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  end
end
