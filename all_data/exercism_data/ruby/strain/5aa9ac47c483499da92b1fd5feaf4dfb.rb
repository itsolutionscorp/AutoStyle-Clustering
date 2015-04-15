class Array
  def keep(&block)
    [].tap do |new_arr|
      self.each do |el|
        new_arr << el if block.call(el)
      end
    end
  end

  def discard(&block)
    self.keep { |el| !block.call(el) }
  end
end
