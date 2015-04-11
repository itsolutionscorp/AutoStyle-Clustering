#!/usr/bin/env ruby

# Exercism 22
# Strain Test

module Strain

  def keep
    result = Array.new
    self.each { |x| result << x if yield(x) }
    result
  end

  def discard
    result = Array.new
    self.each { |x| result << x if not yield(x) }
    result
  end

end

class Array
  include Strain
end
