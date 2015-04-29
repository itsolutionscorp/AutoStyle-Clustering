require 'prime'

class Raindrops
  def self.convert(type)
    output = ""
    if type == 1
      output += "1"
    end
    if prime_factors(type).include?(3)
      output += "Pling"
    end
    if prime_factors(type).include?(5)
      output += "Plang"
    end
    if prime_factors(type).include?(7)
      output += "Plong"
    end
    output += "#{type}" if output.empty?
    output
  end

  def self.prime_factors(num)
    factors = []
    (2..num).to_a.each do |i|
      factors << i if num % i == 0 && Prime.prime?(i)
    end
    factors
  end
end
