require 'prime'

module NthPrime

  def nth index
    raise ArgumentError unless index > 0

    take( index ).last
  end

end

Prime.send :extend, NthPrime
