class Array

  def keep(&block)
    self.each_with_object([]) { |item, results| results << item if block.call(item) }
  end

  def discard(&block)
    self.each_with_object([]) { |item, results| results << item unless block.call(item) }
  end

end
