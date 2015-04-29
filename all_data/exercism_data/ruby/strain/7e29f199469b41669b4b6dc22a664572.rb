class Array
  def keep &block
    self.each_with_object(Array.new) do |element, list|
      list << element if block.call(element)
    end
  end
  def discard &block
    self.each_with_object(Array.new) do |element, list|
      list << element unless block.call(element)
    end
  end
end
