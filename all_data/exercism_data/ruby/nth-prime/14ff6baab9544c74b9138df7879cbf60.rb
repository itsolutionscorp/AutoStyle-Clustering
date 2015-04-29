class Prime
  def self.nth(num)
    num > 0 or raise ArgumentError, "nth input cannot be less than 1"
    
    primecount = 1
    i = 2

    loop do 
      return i if primecount == num
      i += 1
      primecount += 1 if i.prime?
    end
  end
end

class Fixnum
  def prime?
    (2..Math.sqrt(self).floor).none? { |divisor| self % divisor == 0 }
  end
end
