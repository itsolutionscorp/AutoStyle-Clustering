class Array
  def keep
    self.each_with_object([]){|el, result| result << el if yield el}
  end

  def discard
    self.each_with_object([]){|el, result| result << el unless yield el}
  end
  
end
