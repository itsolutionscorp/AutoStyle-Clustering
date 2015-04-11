require 'prime'

class Raindrops
  RAINDROPS = {3 => "Pling", 5 => "Plang", 7 => "Plong" }
  RAINDROPS.default = ""
  
  def self.convert(number) 
    conversion  = ""
    self.get_relevant_primes(number).each { |prime| conversion += RAINDROPS[prime] }
    conversion.empty? ? number.to_s : conversion
  end
  
  private
  
  def self.get_relevant_primes(number)
    prime_factors = Prime.prime_division(number)
    relevant_primes = prime_factors.flatten.delete_if { |prime| prime > 7 }.uniq
  end
end
