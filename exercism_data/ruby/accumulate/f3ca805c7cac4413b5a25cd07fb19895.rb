class Array
  def accumulate
    self.each_with_object([]) do |element, resulting_array|
      resulting_array << (yield element)
    end
  end
end
