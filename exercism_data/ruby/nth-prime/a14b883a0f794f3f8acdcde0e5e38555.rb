class Prime
  def self.nth(num)
    num > 0 or raise ArgumentError, "nth input cannot be less than 1"
    
    primecount = 0
    i = 2

    loop do 
      primecount += 1 if i.prime?
      return i if primecount == num
      i += 1
    end
  end
end

class Fixnum
  def prime?
    (2..Math.sqrt(self).floor).each do |i|
      return false if self % i == 0
    end
  end
end
