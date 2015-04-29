require 'prime'
class Raindrops
  def self.convert(number)
    prime_factoriazation = Prime.prime_division(number)
    result_string = ""
    string_drivers = prime_factoriazation.map {|pair| pair.shift}
    result_string << "Pling" if string_drivers.include?(3)    
    result_string << "Plang" if string_drivers.include?(5)      
    result_string << "Plong" if string_drivers.include?(7)    
    result_string << number.to_s if result_string.empty?
    result_string
  end
end
