class Raindrops

  def self.convert(num)
    primes = num.factorize
    primes.uniq!
    speak  = ""
    primes.each.map { |p|
      if p == 3
        speak += "Pling"
      elsif p == 5
        speak += "Plang"
      elsif p == 7
        speak += "Plong"
      end
    }
    if speak.empty?
      speak = "#{num}"
    end
    speak
  end

end

class Integer
  require 'prime'

  def factorize
    num = self
    ogNum = num
    factors = []
    sqr = Math.sqrt(num)


    while ( num % 2 == 0 )
      factors.push(2)
      num = num / 2
    end

    index = 3

    loop do
      fLen = factors.length

      while ( num % index == 0 )
        factors.push(index)
        num = num / index
      end
      if fLen == factors.length
        index += 2
      end
      if num.prime?
        factors.push(num)
      end
      break if num.prime? || index > sqr
    end

    if ogNum.prime?
      print "#{ogNum} is prime"
    elsif num > sqr
      return factors
    end

    factors
  end

end



# First, find prime factors of num, store in array
# Second, use uniq! method on array of prime factors
# Third, look for 3, 5, or 7 in array, and if so, create a string with Pling Plang and/or Plong
# If these numbers aren't present, or if prime factors are less than or equal to 3, return original
# Finally, output created string
