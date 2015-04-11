class PrimeFactors
  require 'prime'

  def self.for(num)
    number = num
    result = []
    prime = 2
    while prime <= number
      if number % prime == 0
        result << prime
        number /= prime
      else
        prime += 1
        prime += 1 until prime.prime?
      end
    end
    result
  end

end
