class Array
  def keep
    result = []
    self.each do |object|
      if yield object
        result << object
      end
    end
    result
  end

  def discard
    result = []
    self.each do |object|
      unless yield object
        result << object
      end
    end
    result
  end
end
