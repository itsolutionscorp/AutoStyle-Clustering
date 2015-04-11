require 'prime'

class PrimeFactors
  class << self
    def for(num)
      Prime.each(nil, Prime::Generator23.new).inject([]) do |factors, prime|
        return factors if num < prime
        quotient, remainder = num.divmod(prime)
        if remainder == 0
          num = quotient
          factors << prime
          redo
        end
        factors
      end
    end
  end
end
