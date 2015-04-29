module Strainable

  def keep
    each.with_object([]) do |item, array|
      array << item if yield(item)
    end
  end

  def discard
    keep { |item| !yield(item) }
  end

end

class Array

  include Strainable

end
