require 'prime'

class Sieve

  def initialize given_limit
    @given_limit = given_limit
  end

  def primes 
    @result = []

    (2..@given_limit).each do |x| 
       if Prime.prime?(x) == true 
        @result << x
       end
    end
    @result
  end
end
