class Array
  def accumulate
    self.each_with_index do |element, i|
      self[i] = yield element
    end
  end
end
