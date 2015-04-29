class Array

  def keep
    result = []
    self.each { |item| result << item if yield(item) }
    result
  end

  def discard
    result = []
    self.each { |item| result << item unless yield(item) }
    result
  end
end
