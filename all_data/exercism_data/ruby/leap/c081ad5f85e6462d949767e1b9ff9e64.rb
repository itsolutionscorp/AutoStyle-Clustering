module Divisible
  def divisible_by? n
    self % n == 0
  end
end

Numeric.send(:include, Divisible)

module Year
  def self.leap? y
    return true  if  y.divisible_by? 400
    return false if  y.divisible_by? 100
    return y.divisible_by? 4
  end
end
