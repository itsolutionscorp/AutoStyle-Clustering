class Array
  def keep
    self.map! do |element|
      element if yield(element)
    end.compact
  end

  def discard
    self.map! do |element|
      element unless yield(element)
    end.compact
  end
end
