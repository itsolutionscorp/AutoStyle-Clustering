class Array
  def keep
    arr = []
    self.each do |char|
      if yield(char)
        arr << char
      end
    end
    arr
  end

  def discard
    arr = []
    self.each do |char|
      if !yield(char)
        arr << char
      end
    end
    arr
  end
end
