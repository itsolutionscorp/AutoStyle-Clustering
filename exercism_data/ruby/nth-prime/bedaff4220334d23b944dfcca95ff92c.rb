require 'prime'
module NthPrime
  def nth(num)
    take(num).last || raise(ArgumentError)
  end
end

Prime::extend ::NthPrime
