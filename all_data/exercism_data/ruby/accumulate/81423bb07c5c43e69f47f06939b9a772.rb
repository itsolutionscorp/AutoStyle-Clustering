module Accumulate
  def accumulate
    # Return enumerator if called without a block
    return enum_for(:accumulate) unless block_given?
    
    # Assume that including objects respond to each and <<
    self.class.new.tap do |result|
      each do |value|
        result << yield(value)
      end
    end
  end
end

# Monkey-patch array with Accumulate methods
class Array
  include Accumulate
end
