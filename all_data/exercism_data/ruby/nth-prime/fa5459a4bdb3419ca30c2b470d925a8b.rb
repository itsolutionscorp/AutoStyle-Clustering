require 'prime'

module NthPrime
  def nth(n)
    raise ArgumentError if n <= 0
    first(n).last
  end
end

Prime.extend NthPrime
