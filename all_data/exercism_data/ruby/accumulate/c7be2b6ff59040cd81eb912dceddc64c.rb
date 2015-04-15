module Accumulatable
  def accumulate
    each.with_object([]) { |elem, acc| acc << yield(elem) }
  end
end

class Array
  include Accumulatable
end
