class Array
  def keep(&predicate)
    new_collection = []
    self.each {|element| new_collection << element if predicate.call(element)}
    new_collection
  end
  
  def discard(&predicate)
    new_collection = []
    self.each {|element| new_collection << element unless predicate.call(element)}
    new_collection
  end
end
