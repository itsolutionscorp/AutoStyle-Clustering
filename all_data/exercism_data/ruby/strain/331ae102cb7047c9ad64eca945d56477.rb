class Array
  def keep(&block)
    self.each_with_object([]) do |el, new_arr|
      new_arr << el if block.call(el)
    end
  end

  def discard(&block)
    self.keep { |el| !block.call(el) }
  end
end
