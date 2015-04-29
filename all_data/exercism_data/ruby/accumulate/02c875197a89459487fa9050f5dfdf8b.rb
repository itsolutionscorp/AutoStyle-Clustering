#!/usr/bin/env ruby

# Exercism 16
# Accumulate
# Without using collect/map/fmap

module Accumulate

  def accumulate
    result = Array.new
    self.each { |x| result << yield(x) }
    result
  end

end

class Array
  include Accumulate
end
