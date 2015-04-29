class Array
  def keep(&predicate)
    each_with_object([]) {|element, new_collection| new_collection << element if predicate.call(element)}
  end
  
  def discard(&predicate)
    each_with_object([]) {|element, new_collection| new_collection << element unless predicate.call(element)}
  end
end
