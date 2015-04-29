require 'prime'

class Prime
  class << self
    def nth n
      unless n.is_a? Integer and n > 0
        raise ArgumentError, 'n must be a positive integer'
      end
      Prime.take(n).last
    end
  end
end
