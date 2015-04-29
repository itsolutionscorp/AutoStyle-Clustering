class Prime
  require 'prime'

  class << self

    # Returns the nth prime number
    def nth(n)
      raise ArgumentError, 'Argument must be greater than 0' unless n > 0
      parr = Prime.first n
      parr[-1]
    end

  end
end
