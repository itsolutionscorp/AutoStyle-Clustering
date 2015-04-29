require 'prime'
class Raindrops
  def self.convert(number)
    found = "" 
    convert = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    factors = Prime.prime_division(number)
    factors.each { |factor,_| found << convert.fetch(factor,"") }
    found.empty? ? number.to_s : found
  end
end
