require 'Prime'

module NthPrime
  def nth number
    unless number.zero?
      take( number ).last
    else
      raise ArgumentError
    end
  end
end

Prime.send :extend, NthPrime
