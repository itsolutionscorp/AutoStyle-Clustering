class Array

  def keep
    self.each.with_object([]) do |item, array|
      array << item if yield(item)
    end
  end

  def discard
    self.each.with_object([]) do |item, array|
      array << item unless yield(item)
    end
  end

end
