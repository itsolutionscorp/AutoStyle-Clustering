class Array
  
  def keep(&block)
    self.each_with_object([]) do |element, new_collection|
      block.call(element) ? new_collection << element : new_collection
    end
  end
  
  def discard(&block)
    keep {|element| !block.call(element)}
  end
  
end
