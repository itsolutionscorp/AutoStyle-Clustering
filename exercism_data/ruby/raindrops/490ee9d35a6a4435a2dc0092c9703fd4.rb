require 'prime'


class Raindrops
 
  def self.convert(num)
    string = ""
    factors = []

    rain = Hash[3 => "Pling", 5 => "Plang", 7 => "Plong"]
    
    Prime.prime_division(num).each {|p| factors << p}
    
    factors = [3, 5, 7].select{|i| factors.flatten.include?(i)}

    if factors.empty?
      string = num.to_s
    else
      factors.each {|r| string += rain[r]}
    end
    
    string
  end
end
