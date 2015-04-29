module Strainable
  def keep
    each_with_object([]) do |element, result|
      result << element if yield(element)
    end
  end

  def discard
    keep { |item| !yield(item) }
  end
end

class Array
  include Strainable
end
