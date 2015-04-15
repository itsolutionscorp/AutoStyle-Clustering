module Accumulatable
  def accumulate
    each_with_object([]) { |e, result| result << yield(e) }
  end
end

class Array
  include Accumulatable
end
