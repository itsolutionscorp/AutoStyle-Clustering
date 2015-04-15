require 'prime'
class Raindrops
  def self.convert(number)
    found = "" 
    convert = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    convert.default=""
    factors = Prime.prime_division(number)
    factors.each { |factor,_| found << convert[factor] }
    found.empty? ? number.to_s : found
  end
end
