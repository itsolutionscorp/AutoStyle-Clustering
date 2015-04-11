class Array
  def keep
    self.each_with_object([]) do |item, keeps|
      keeps << item if yield item
    end
  end

  def discard
    self.each_with_object([]) do |item, keeps|
      keeps << item unless yield item
    end
  end
end
