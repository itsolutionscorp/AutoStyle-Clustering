require 'prime'

class Raindrops
  def self.convert(number)
    prime_factors = prime_factors(number)
    string = ''
    prime_factors.each do |prime_factor|
      if prime_factor == 3
        string = string + 'Pling'
      elsif prime_factor == 5
        string = string + 'Plang'
      elsif prime_factor == 7
        string = string + 'Plong'
      end
    end
    if string.empty?
      number.to_s
    else
      string
    end
  end


  private
  def self.prime_factors(number)
    number.prime_division.flatten
  end
end
