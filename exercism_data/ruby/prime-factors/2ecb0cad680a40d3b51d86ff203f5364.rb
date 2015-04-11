require 'prime'

class PrimeFactors
  class << self
    def for(number)
      Prime.instance.prime_division(number).inject([]){|factors, (factor, index)| factors + [factor] * index}
    end
  end
end
