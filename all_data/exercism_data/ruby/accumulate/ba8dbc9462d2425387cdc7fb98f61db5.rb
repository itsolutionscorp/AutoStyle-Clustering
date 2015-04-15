module Accumulate
  def accumulate
    out = []
    each { |x| out << yield(x) }
    out
  end
end

class Array
  include Accumulate
end
