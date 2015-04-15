require 'prime'

class Raindrops
  def self.convert(number)
    prime_factors = Prime.prime_division(number).map { |primes| primes.first }
    
    sound = ""
    sound << 'Pling'  if prime_factors.include?(3)
    sound << 'Plang'  if prime_factors.include?(5)
    sound << 'Plong'  if prime_factors.include?(7)
    
    sound.empty? ? number.to_s : sound
  end
end
