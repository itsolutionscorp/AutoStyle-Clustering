class Array
  def accumulate
    self.each_with_object([]) do |elem, array|
      array << yield(elem)
    end
  end
end
