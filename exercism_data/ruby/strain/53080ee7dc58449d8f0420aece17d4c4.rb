class Array
  def keep(&block)
    self.each_with_object([]) do |el, result|
      if yield el
        result << el
      end
    end
  end

  def discard(&block)
    self.each_with_object([]) do |el, result|
      unless yield el
        result << el
      end
    end
  end
end
