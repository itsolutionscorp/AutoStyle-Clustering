require 'prime'

class PrimeFactors
  def PrimeFactors.for (n)
    Prime.prime_division(n).collect_concat{|p, n| Array.new(n, p) }
  end
end
