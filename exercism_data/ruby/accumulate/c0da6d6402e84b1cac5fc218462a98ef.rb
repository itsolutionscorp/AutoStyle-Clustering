module Accumulate
  def accumulate
    each_with_object([]) { |x, out| out << yield(x) }
  end
end

class Array
  include Accumulate
end
