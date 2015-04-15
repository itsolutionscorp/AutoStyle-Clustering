module Accumulate
  def accumulate &block
    self.each_with_index do |element, index| 
      self[ index ] = block.call( element )
    end  
  end
end

class Array
  include Accumulate
end
