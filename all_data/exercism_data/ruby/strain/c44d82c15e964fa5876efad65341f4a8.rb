class Array

  def keep
    result = []
    self.each do |element|
      result << element if yield element
    end
    result
  end

  def discard
    result = []
    self.each do |element|
      result << element unless yield element
    end
    result
  end

 end
