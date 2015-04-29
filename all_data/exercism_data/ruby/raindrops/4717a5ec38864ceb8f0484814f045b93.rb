class Fixnum
  def firstDivisor
    (2..self).each do |divisorCandidate|
      return divisorCandidate if self % divisorCandidate == 0
    end
  end
end

class Array

  def includeAny otherArray
    otherArray.each {|item|
      return true if self.include? item
    }
    return false
  end

end

class Raindrops

  RAINDROP_SPEAK = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.primeFactors number
    factors = []
    while number != 1
      factor = number.firstDivisor
      factors << factor
      number /= factor
    end
    factors
  end

  def self.convert number
    factors = primeFactors number
    if factors.includeAny RAINDROP_SPEAK.keys then
      raindropSpeak = ''
      RAINDROP_SPEAK.each {|factor, translation|
        raindropSpeak << translation if factors.include? factor
      }
      return raindropSpeak
    else
      return number.to_s
    end
  end

end
