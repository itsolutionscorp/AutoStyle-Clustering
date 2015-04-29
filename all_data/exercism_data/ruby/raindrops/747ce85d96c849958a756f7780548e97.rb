require 'prime'

class Raindrops
  
  def convert(input)
  raindrops = ""
  primes = input.prime_division
  primes.each do |prime, total|
    if prime == 3 then  
      raindrops.concat("Pling")
    elsif prime == 5 then  
      raindrops.concat("Plang") 
    elsif prime == 7 then 
      raindrops.concat("Plong") 
    end
   end
  if raindrops == ""
    return input.to_s
  else 
    return raindrops
  end
    end
 end
