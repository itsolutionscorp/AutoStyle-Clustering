class Array
  def accumulate
    self.each_with_object([]) do |element, array|
    	array << yield(element)
    end
  end
end
