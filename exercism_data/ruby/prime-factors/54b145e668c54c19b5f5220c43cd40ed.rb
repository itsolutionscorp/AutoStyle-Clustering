require 'prime'

module PrimeFactors
  def self.for(n)
    return [] if  n < 2
    return [n] if n.prime?
    first_factor = first_factor(n)
    [first_factor] + self.for(n/first_factor)
  end

  private

  def self.first_factor(n)
    2.upto(n) do |i|
      return i if i.prime? && n%i == 0
    end
  end

end
