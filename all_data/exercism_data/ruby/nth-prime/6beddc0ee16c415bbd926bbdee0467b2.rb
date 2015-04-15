require 'Prime'

module NthPrime
  def self.nth number
    unless number.zero?
      take( number ).last
    else
      raise ArgumentError
    end
  end
end

Prime.send :extend, NthPrime
