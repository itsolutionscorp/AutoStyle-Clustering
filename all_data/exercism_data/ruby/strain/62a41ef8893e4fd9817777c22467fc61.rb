class Array

  def keep
    result = []
    self.each_with_index do |element, i|
      result << element if yield element
    end
    result
  end

  def discard
    result = []
    self.each_with_index do |element, i|
      result << element unless yield element
    end
    result
  end

 end
