class Array
  
  def keep
    self.each_with_object([]) do |element, new_collection|
      new_collection << element if (yield element)
    end
  end
  
  def discard(&block)
    keep {|element| !block.call(element)}
  end
  
end
