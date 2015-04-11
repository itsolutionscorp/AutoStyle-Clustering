# prime_factors.rb
class PrimeFactors
  def self.for(n)
    return [] if n == 1
    2.upto(n) do |divisor|
      quotient, remainder = n.divmod(divisor)
      return self.for(quotient).unshift(divisor) if remainder.zero?
    end
  end
end
