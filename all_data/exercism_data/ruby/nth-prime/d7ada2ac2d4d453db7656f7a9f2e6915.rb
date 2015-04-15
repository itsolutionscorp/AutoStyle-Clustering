class Prime
  require 'prime'

  class << self

    # Returns the nth prime number
    def nth(n)
      raise ArgumentError, 'Argument must be greater than 0' unless n > 0
      Prime.first(n).last
    end

  end
end
