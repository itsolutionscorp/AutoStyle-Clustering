class Array
  
  def keep
    each_with_object([]) {|x, obj| obj << x if yield x}
  end

  def discard
    each_with_object([]) {|x, obj| obj << x unless yield x}
  end

end
