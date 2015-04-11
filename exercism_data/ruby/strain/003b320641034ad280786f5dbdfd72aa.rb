module Strainable

  def keep
    self.map { |x| x if yield(x) }.compact if block_given?
  end

  def discard
    keep { |item| !yield(item) } if block_given?
  end

end

class Array  
  include Strainable
end
