class Raindrops
  @@primes = {3=>"Pling",5=>"Plang",7=>"Plong"}
  def self.convert(number)
    result = ''
    result = number.to_s if self.is_not_prime(number) 
    if number % 3 == 0 
      result+="Pling"  
    end
    if number % 5 == 0
      result += "Plang"
    end
    if number % 7 == 0
      result += "Plong"
    end
    result
  end
  def self.is_not_prime(number)
    return number % 3 != 0 && number % 5 != 0 && number % 7 != 0
  end
end
