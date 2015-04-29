class Array
  def keep
    self.each_with_object([]) do |i, result|
      if yield(i)
        result << i
      end
    end
  end

  def discard
    self.each_with_object([]) do |i, result|
      unless yield(i)
        result << i
      end
    end
  end
end
