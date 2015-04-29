module Accumulate
  def accumulate
    # Return enumerator if called without a block
    return enum_for(:accumulate) unless block_given?
    
    # Assume that including objects respond to each and <<
    result = self.class.new
    each do |value|
      result << yield(value)
    end
    result
  end
end

# Monkey-patch array with Accumulate methods
class Array
  include Accumulate
end
