class Numeric
  def divisible_by?( n )
    self % n == 0
  end
end

module Raindrops
  module_function
  def convert( n )
    result = ""
    result << "Pling" if n.divisible_by? 3
    result << "Plang" if n.divisible_by? 5
    result << "Plong" if n.divisible_by? 7
    return n.to_s if result.empty?
    result
  end
end
