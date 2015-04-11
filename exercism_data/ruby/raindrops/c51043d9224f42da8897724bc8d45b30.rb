
class Integer
  def isPrime?
    if self<2
      false
    elsif self==2
      true
    else
      upperBound = Math.sqrt(self).round +1
      range = 2..upperBound
      !range.any?{|n| self%n==0}
    end
  end
end

class Raindrops
  @@rainspeak = {3=>"Pling", 5=>"Plang", 7=>"Plong"}

  def self.convert(d)
    primeFactors = (2..d).select{|n| d%n==0 and n.isPrime?}
    noise = ""
    for n, word in @@rainspeak
      if primeFactors.include? n
        noise += @@rainspeak[n]
      end
    end

    if noise=="" then d.to_s else noise end
  end
end

print Raindrops.convert(50)





