class Array
  
  def keep(&block)
    self.inject([]) do |new_collection, element|
      new_collection << element if block.call(element)
      new_collection
    end
  end
  
  def discard(&block)
    self.inject([]) do |new_collection, element|
      new_collection << element unless block.call(element)
      new_collection
    end
  end
end
