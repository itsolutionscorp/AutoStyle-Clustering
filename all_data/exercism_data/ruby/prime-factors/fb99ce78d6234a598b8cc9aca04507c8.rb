require 'prime'

module PrimeFactors
  extend self

  def for(number)
    Prime.prime_division(number).each_with_object([]) do |(factor, n), result|
      n.times { result << factor }
    end
  end

end
