class Primeresult

  def self.for number
    result = []

    while number != 1  
     2.upto( number ).find do |divisor|  
        if number % divisor == 0
          result << divisor
          number /= divisor
        end
      end
    end

    result
  end

end

# I have seen other people doing it in different ways. The one I liked the most even 
# it is not differing a lot is this second one. Can anybody tell me which is better in 
# terms of rubyism and speed?

class PrimeFactors

  def self.for(number)
    result = []
    divisor = 2

    while number > 1
      while number % divisor == 0
        result << divisor
        number /= divisor
      end
      divisor += 1
    end

    result
  end
end
