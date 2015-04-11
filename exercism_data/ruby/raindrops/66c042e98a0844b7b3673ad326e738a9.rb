require 'prime'

class Raindrops
  def self.convert(number)
    factors = number.prime_division.flatten
    string = ''

    factors.each do |n|
      if n == 3
        string += 'Pling'
      elsif n == 5
        string += 'Plang'
      elsif n == 7
        string += 'Plong'
      else
        number.to_s
      end
    end
    string.empty? ? number.to_s : string
  end
end
