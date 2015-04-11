require 'prime'

class PrimeFactors
  def self.for(x)
    Prime.each(x).each_with_object([]) do |p, a|
      while x % p == 0
        a << p
        x /= p
      end	
      return a if x == 1
    end
  end
end
