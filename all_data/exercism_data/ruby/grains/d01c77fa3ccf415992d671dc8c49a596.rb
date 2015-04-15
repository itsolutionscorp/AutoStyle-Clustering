require 'singleton'

# Basic optimisation: cache any generated values.
# As all values are static it could be optimised further by just hardcoding everything.
# Time for 1 million tests of square_64 and total_grains 3 sec vs 22 sec without
# optimisation.
class SingleGrain
  include Singleton

  def initialize
    @squares = Array.new(64)
    @squares[0] = 1
    63.times { |i| @squares[i + 1] = @squares[i] * 2 }
    @total = @squares.inject(:+)
  end

  def square(n)
    @squares[n - 1]
  end

  attr_reader :total
end

class Grains
  def self.new
    SingleGrain.instance
  end
end
